package com.example.android.graminkendra

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_sign.*
import kotlinx.android.synthetic.main.custom_dialog_box.*


class signActivity : baseactivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        auth= FirebaseAuth.getInstance()

        signInFirebase.setOnClickListener {

            signInRegisterActivity()

        }
    }
    private  fun signInRegisterActivity(){
        val eMail:String=mailAddressSignIn.text.toString().trim { it <=' ' }
        val password1:String=passwordS.text.toString().trim { it <=' ' }
        if(isVaildate(eMail,password1)){
            showProgressDialog("Please wait")
            auth.signInWithEmailAndPassword(eMail, password1)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                            userSignSuccessInActivity()
                        Log.e("Successfully", "signInWithEmailActivity:success")

                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e("failed", "signInWithEmailActivity:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                               hideDialog()
                    }
                }
        }

    }
    fun userSignSuccessInActivity(){
        hideDialog()
        dialogBox()
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
    private fun user(){

        var user1=User(nameOfEmployee.text.toString(),mailAddressSignIn.text.toString())
        FirestoreClass().registerUser(this,user1)

    }
    fun profileUpdateSuccess() {
        hideDialog()
    }
    private fun dialogBox() {
        var dialogSign = Dialog(this)
        dialogSign.setContentView(R.layout.custom_dialog_box)

        dialogSign.tvYes.setOnClickListener {
            user()
            startActivity(Intent(this,information::class.java))
            dialogSign.dismiss()
            finish()

        }
        dialogSign.tvNo.setOnClickListener {
            dialogSign.dismiss()
            startActivity(Intent(this,information::class.java))
finish()


        }
        dialogSign.show()
    }
}