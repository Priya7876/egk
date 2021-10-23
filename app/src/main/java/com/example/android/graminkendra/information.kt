package com.example.android.graminkendra

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.android.graminkendra.scheme
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_info_c.*
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.custom_dialog_box.*
import kotlinx.android.synthetic.main.nav_header_main.*

class information : baseactivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        var currentUser=FirestoreClass().getCurrentUserId()
        if(currentUser.isNotEmpty()) {
            setaUpActionBar1()
            FirestoreClass().UsweFireData(this,true)

        }
        else{
            setActionBar()
        }

        nav_view.setNavigationItemSelectedListener(this)

        onClickListeners()


    }
    fun setActionBar() {
        setSupportActionBar(toolbarInformation)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Information"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarInformation.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setaUpActionBar1()  {
        setSupportActionBar(toolbarInformation)
        toolbarInformation.setNavigationIcon(R.drawable.ic_baseline_menu_24)
        toolbarInformation.title="Information"
        toolbarInformation.setNavigationOnClickListener {
            toggleDrawer();
        }
    }

    fun onClickListeners(){
        complaints.setOnClickListener {
            var intent = Intent(this,infoC::class.java)
            startActivity(intent)
        }
      scheme.setOnClickListener {
            var intent = Intent(this,infoS::class.java)
            startActivity(intent)
        }
        meetingDate.setOnClickListener {
            var intent = Intent(this,infoM::class.java)
            startActivity(intent)
        }
        covidCrisis.setOnClickListener {
            var intent =Intent(this,covidInfo::class.java)
            startActivity(intent)
        }
    }
    private  fun toggleDrawer(){
        if(my_drawer_layout.isDrawerOpen(GravityCompat.START)){
            my_drawer_layout.closeDrawer(GravityCompat.START)
        }
        else{

            my_drawer_layout.openDrawer(GravityCompat.START)

        }
    }
    override fun onBackPressed() {
        if(my_drawer_layout.isDrawerOpen(GravityCompat.START)){
            my_drawer_layout.closeDrawer(GravityCompat.START)
        }
        else{

            doubleBackToExit()        }
        super.onBackPressed()
    }




   override  fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_my_profile->{
                val intent= Intent(this,myProfileActivity::class.java)
                startActivity(intent)

            }
            R.id.signOut->{
                FirebaseAuth.getInstance().signOut()
                val intent= Intent(this,IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent, life)
                finish()
            }
        }
        my_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun updateNavigationBar(user: User) {
        Glide
            .with(this@information)
            .load(user.image1)
            .centerCrop()
            .placeholder(R.drawable.placeholder).dontAnimate()
            .into(nav_header)
        main_NameE.text=user.name



    }

companion object {
    const val life =11
}
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== Activity.RESULT_OK&& requestCode== life){
FirestoreClass().UsweFireData(this)
        }



        super.onActivityResult(requestCode, resultCode, data)
    }

}