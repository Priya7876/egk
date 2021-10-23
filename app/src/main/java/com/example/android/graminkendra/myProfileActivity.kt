package com.example.android.graminkendra

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_add_complaint.*
import kotlinx.android.synthetic.main.activity_addition_of_event.*
import kotlinx.android.synthetic.main.activity_info_c.*
import kotlinx.android.synthetic.main.activity_my_profile.*

class myProfileActivity : baseactivity() {
    var  savedImage: Uri?=null
    private var mBoardImageUrl:String=""
    lateinit var mUserDetail:User

    companion object{
        private const val   GALLERY2=11}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        setActionBar()
        FirestoreClass().UsweFireData(this,true)
        imageForProfile.setOnClickListener {
            choosePhotoFromGallery()
        }
       saveChanges.setOnClickListener {

           if(savedImage!=null){
               uploadBoardImage()
           }

           else{
               showProgressDialog("Please Wait")


               updateUserProfileData()

           }

       }
    }
  private  fun setActionBar() {
        setSupportActionBar(toolbarMyProfile)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="My Profile"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarMyProfile.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    fun profileUpdateSuccess() {
        hideDialog()
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun choosePhotoFromGallery(){
        Dexter.withContext(this)
            .withPermissions(

                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if(report.areAllPermissionsGranted()){
                        val galleryIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                        startActivityForResult(galleryIntent, GALLERY2 )
                    }



                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) { showRationale()
                }
            }).onSameThread().check()

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            if(requestCode== GALLERY2){
                if(data!=null){
                    savedImage=data.data
                    try{
                        Glide
                            .with(this@myProfileActivity)
                            .load(savedImage)
                            .centerCrop()
                            .placeholder(R.drawable.placeholder).dontAnimate()
                            .into(imageForProfile)

                    }
                    catch (e:Exception){
                        Log.e("error","error in image uploading")
                    }}
            }




        }

    }
    private fun showRationale() {
        AlertDialog.Builder(this) .setMessage("It look like you have denied the read/write access!!If you wanna to access then go to setting")
            .setPositiveButton("Go to setting"){
                    _,_->
                try{
                    val intent= Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri= Uri.fromParts("package","PackageName",null)
                    intent.data=uri
                    startActivity(intent)
                }
                catch (e: ActivityNotFoundException){
                    e.printStackTrace()
                }
            }

            .setNegativeButton("Cancel"){
                    dialog,which ->
                dialog.dismiss()


            }.show()
    }
    private  fun getFileExtension(uri: Uri):String?{
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(contentResolver.getType(uri!!))
    }
    private fun uploadBoardImage(){
        showProgressDialog("Please Wait")
        if(savedImage!=null){
            val sref: StorageReference = FirebaseStorage.getInstance().reference.child(
                "boards"+System.currentTimeMillis()+"."+getFileExtension(savedImage!!))

            sref.putFile(savedImage!!).addOnSuccessListener {
                    taskSnapShot->
                Log.e("Fire Base Image URL",taskSnapShot.metadata!!.reference!!.downloadUrl.toString())

                taskSnapShot.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                        uri->
                    Log.e("Board Image  Url",uri.toString())
                    mBoardImageUrl=uri.toString()
                   updateUserProfileData()


                } } .addOnFailureListener{

                    exception->
                Toast.makeText(this,"Storage Failed", Toast.LENGTH_SHORT).show()
                hideDialog()
            }
        }
    }


    fun updateMyProfile(user: User) {
        mUserDetail = user
        Glide
            .with(this@myProfileActivity)
            .load(user.image1)
            .centerCrop()
            .placeholder(R.drawable.placeholder).dontAnimate()
            .into(imageForProfile)
        nameUpdate.setText(user.name)
        emailOFEmployee.setText(user.email)
        position.setText(user.position)
            mobileUpdate.setText(user.mobile.toString())



    }

    fun updateUserProfileData(){
        val hashMap=HashMap<String,Any>()
        if(mBoardImageUrl!=mUserDetail.image1&& mBoardImageUrl.isNotEmpty()){
            hashMap[constants.IMAGE]=mBoardImageUrl
        }
        if(nameUpdate.text.toString()!=mUserDetail.name.toString()){
            hashMap[constants.NAME1]=nameUpdate.text.toString()
        }
        if(position.text.toString()!=mUserDetail.position){
            hashMap[constants.POSITION]=position.text.toString()
        }
        if(mobileUpdate.text.toString()!=mUserDetail.mobile.toString()){
            hashMap[constants.MOBILE]=mobileUpdate.text.toString()
        }
        FirestoreClass().updateUserProfileData(this,hashMap)
    }

}