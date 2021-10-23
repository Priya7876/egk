package com.example.android.graminkendra

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast

import com.thecode.aestheticdialogs.*
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.activity_sign.*

class IntroActivity : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        seeUpdateButton.setOnClickListener {
            Toast.makeText(this@IntroActivity,"See updates as a guest",Toast.LENGTH_SHORT).show()
            val intent=Intent(this,information::class.java)
            startActivity(intent)
        }
        SignButton.setOnClickListener {
            customDialog()
        }

    }
    private fun customDialog(){

        AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.WARNING)
            .setTitle("Hey")
            .setMessage("If you are an employee of the Panchayat,then Sign In")
            .setCancelable(true)
            .setDarkMode(false)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    val intent=Intent(this@IntroActivity,signActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
            .show()



                        }}