package com.example.android.graminkendra

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_info_c.*
import kotlinx.android.synthetic.main.activity_info_m.*
import kotlinx.android.synthetic.main.dialogaccess.*

class infoM : baseactivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_m)
        setActionBar()
        FirebaseApp.initializeApp(this)
        auth= FirebaseAuth.getInstance()
        var currentUser=FirestoreClass().getCurrentUserId()

        addMeetFab.setOnClickListener {
            if(currentUser.isNotEmpty()){
                showProgressDialog("Please wait")
                userSignSuccess()
            }
            else{
                showErrorSnackBar("only available to Panchayat's personnel")}
        }
        FirestoreClass().getMeetList(this)
    }
    fun setActionBar() {
        setSupportActionBar(toolbarMeeting)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Events"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarMeeting.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun dialogSearchMember() {
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.dialogaccess)
        dialog.tv_signIn.setOnClickListener {
            val eMail: String = dialog.et_email.text.toString().trim { it <= ' ' }
            val password1: String = dialog.et_password.text.toString().trim { it <= ' ' }
            if (eMail.isNotEmpty() && password1.isNotEmpty()) {


                if (isVaildate(eMail, password1)) {
                    showProgressDialog("Please wait")
                    auth.signInWithEmailAndPassword(eMail, password1)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                userSignSuccess()
                                Log.e("Successfully", "signInWithEmail:success")
                                val user = auth.currentUser

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("failed", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
hideDialog()
                                dialog.dismiss()
                            }
                        }
                }

            } else {
                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT)
            }
            dialog.dismiss()
        }
        dialog.tv_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }



    private  fun  isVaildate(eMail:String,password:String): Boolean {
        return when {

            TextUtils.isEmpty(eMail) -> {
                showErrorSnackBar("Please enter the e-mail")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter the password")
                false
            }
            else ->{
                true
            }
        }
    }
    fun userSignSuccess(){
        hideDialog()
       val intent =Intent(this,additionOfEvent::class.java)
        startActivityForResult(intent, CODE_FOR_INTENT)
    }
    companion object {
        val CODE_FOR_INTENT =10
        var EVENT_SENDING ="detail of meeting is sending"
    }
    fun populateRecyclerView(MeetList:ArrayList<meeting>){
      
       eventsList.layoutManager= LinearLayoutManager(this)
        val adapter2=adapterForMeeting(this,MeetList)
        eventsList.adapter=adapter2
        adapter2.setOnClickListener(object :adapterForMeeting.onClickListener{
            override fun onClick(position: Int, model: meeting) {
                intent = Intent(this@infoM,detail_meeting::class.java)
                intent.putExtra(EVENT_SENDING,model)
                startActivity(intent)
            }

        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK && requestCode== CODE_FOR_INTENT){
            FirestoreClass().getMeetList(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}