package com.example.android.graminkendra

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val typeFace: Typeface = Typeface.createFromAsset(assets,"AndroidInsomniaRegular-RLxW.ttf")
mainName.typeface=typeFace
        main_Name.typeface=typeFace
        Handler().postDelayed({
            var currentUser=FirestoreClass().getCurrentUserId()
if(currentUser.isNotEmpty()) {
startActivity(Intent(this,information::class.java))
}
           else{     startActivity(Intent(this,IntroActivity::class.java))}
                finish()
        },2500)
    }
}