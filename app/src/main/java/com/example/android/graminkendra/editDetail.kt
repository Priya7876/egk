package com.example.android.graminkendra

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_add_on_going_project.*
import kotlinx.android.synthetic.main.activity_edit_detail.*
import kotlinx.android.synthetic.main.activity_edit_detail.view.*
import kotlinx.android.synthetic.main.activity_my_profile.*
import java.util.*
import kotlin.collections.HashMap

class editDetail :baseactivity() {
    private var  savedImage: Uri?=null
    private var mBoardImageUrl:String=""
    lateinit var mUserDetail:project
    private var mSelectedDueDateMilliSecond :Long =0
    lateinit var option : Spinner
    lateinit var result : TextView


    companion object{
        private const val   GALLERY3=11}
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_detail)
        setActionBar()
        updateMyProject()
        startingDateEdit.setOnClickListener {
            showDatePicker()
        }

            showSpinner()

        imageViewForDetailEdit .setOnClickListener {
            choosePhotoFromGallery()
        }
       updateProject.setOnClickListener {

            if(savedImage!=null){
                uploadBoardImage()
            }

            else{
                showProgressDialog("Please Wait")


                updateProjectData()
            }

        }



    }
    private  fun setActionBar() {
        setSupportActionBar(toolEditProjectDetail)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Edit details"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolEditProjectDetail.setNavigationOnClickListener {
            onBackPressed()
        }


    }
    fun ProjectUpdateSuccess() {
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

                        startActivityForResult(galleryIntent, GALLERY3)
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
            if(requestCode==GALLERY3){
                if(data!=null){
                    savedImage=data.data
                    try{
                        Glide
                            .with(this@editDetail)
                            .load(savedImage)
                            .centerCrop()
                            .placeholder(R.drawable.placeholder).dontAnimate()
                            .into(imageViewForDetailEdit)

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
                    updateProjectData()


                } } .addOnFailureListener{

                    exception->
                Toast.makeText(this,"Storage Failed", Toast.LENGTH_SHORT).show()
                hideDialog()
            }
        }
    }
    fun updateMyProject() {
        if(intent.hasExtra(projectInfo.EXTRA_INFO_PROJECT_EDIT)) {
            val projectDetailsEdit =
                intent.getParcelableExtra<project>(projectInfo.EXTRA_INFO_PROJECT_EDIT) as project

            mUserDetail = projectDetailsEdit
            Glide
                .with(this@editDetail)
                .load(projectDetailsEdit.imagePriject)
                .centerCrop()
                .placeholder(R.drawable.placeholder).dontAnimate()
                .into(imageViewForDetailEdit)
            ProjecNameOngoingedit.setText(projectDetailsEdit.projectName)
            descriptionOfProjectEdit.setText(projectDetailsEdit.projectDescription)
            startingDateEdit.setText(projectDetailsEdit.startingDate)
            workersEdit.setText(projectDetailsEdit.noOfWorkers)
            progressPercentEdit.setText(projectDetailsEdit.progessDetail.toString())
            statusOfCompletionEdit.setText(projectDetailsEdit.status)
            progressPercentEdit.setText(projectDetailsEdit.progessDetail.toString())


        }
    }

    fun updateProjectData(){
        val hashMap=HashMap<String,Any>()
        if(mBoardImageUrl!=mUserDetail.imagePriject&& mBoardImageUrl.isNotEmpty()){
            hashMap[constants.IMAGE1]=mBoardImageUrl
        }
        if(ProjecNameOngoingedit.text.toString()!=mUserDetail.projectName){
            hashMap[constants.NAME2]=ProjecNameOngoingedit.text.toString()
        }
        if(descriptionOfProjectEdit.text.toString()!=mUserDetail.projectDescription){
            hashMap[constants.DESCRIPTION]=descriptionOfProjectEdit.text.toString()
        }
        if( startingDateEdit.text.toString()!=mUserDetail.startingDate.toString()){
            hashMap[constants.DATE]=startingDateEdit.text.toString()
        }
        if(   workersEdit.text.toString()!=mUserDetail.noOfWorkers.toString()){
            hashMap[constants.WORKER]=workersEdit.text.toString()
        }
        if(  statusOfCompletionEdit.text.toString()!=mUserDetail.status.toString()){
            hashMap[constants.STATUS]=statusOfCompletionEdit.text.toString()
        }
        if(progressPercentEdit.text.toString().toInt()!=mUserDetail.progessDetail){
            hashMap[constants.PROGRESS]=progressPercentEdit.text.toString().toInt()
        }

        FirestoreClass().updatePROJECTData(this,hashMap,mUserDetail)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDatePicker(){
        val c = Calendar.getInstance()
        val year =c.get(Calendar.YEAR)
        val month =c.get(Calendar.MONTH)
        val day =c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        {
                view, year, month, dayOfMonth ->
            val  SdayOfMonth =if(dayOfMonth<10)  "0${dayOfMonth}" else  "${dayOfMonth}"
            val SMonthOfYear =if((month+1)<10) "0${month+1}" else "${month+1}"
            val selectedDate ="$SdayOfMonth/$SMonthOfYear/$year"
            startingDateEdit.setText(selectedDate)
            val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate =sdf.parse(selectedDate)
            mSelectedDueDateMilliSecond=theDate!!.time},year,month,day

        )
        dpd.show()

    }
    private fun  showSpinner(){
        option=dropDownForStatusEdit as Spinner
        result=statusOfCompletionEdit as TextView
        val options = arrayOf("Just Started","On Going","Completed")
        option.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        option.onItemSelectedListener =object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.text=options.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text="Please Select an Option"
            }

        }
    }

}