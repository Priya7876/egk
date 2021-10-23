package com.example.android.graminkendra

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog.*

open class baseactivity : AppCompatActivity() {
    private var doubleBackToExitPressed=false
    private lateinit var mProgressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseactivity)

            }
            fun showProgressDialog(text:String){
                mProgressDialog= Dialog(this)
                mProgressDialog.setContentView(R.layout.dialog)
                mProgressDialog.nameOfDescription.text=text
                mProgressDialog.show()

            }
            fun hideDialog(){
                mProgressDialog.dismiss()
            }
            fun getCurrentUserId():String{
                return FirebaseAuth.getInstance().currentUser!!.uid
            }
            fun doubleBackToExit(){
                if(doubleBackToExitPressed){
                    super.onBackPressed()
                    return
                }
                this.doubleBackToExitPressed=true
                Toast.makeText(this,"Press once again", Toast.LENGTH_SHORT)
                Handler().postDelayed({
                    doubleBackToExitPressed=false

                },2000)
            }
            fun showErrorSnackBar(message:String){
                val snackBar= Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)
                val snackBarView=snackBar.view
                snackBarView.setBackgroundColor(ContextCompat.getColor(this,R.color.snack1))
                snackBar.show()
            }



        }
