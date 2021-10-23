package com.example.android.graminkendra

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_covid_centre_info.*
import kotlinx.android.synthetic.main.activity_vaccination_info.*

class covidCentreInfo :baseactivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_centre_info)
        setActionBar()
        var currentUser=FirestoreClass().getCurrentUserId()

        CaringFab.setOnClickListener {
            if(currentUser.isNotEmpty()){
                showProgressDialog("Please wait")
                userSignSuccess()
            }
            else{
                showErrorSnackBar("only available to Panchayat's personnel")}
        }
        FirestoreClass().getCovidCaringList(this)

    }
    private fun setActionBar() {
        setSupportActionBar(toolbarCaringInfo)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Isolation & Caring Centres"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarCaringInfo.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    fun populateRecyclerView(CaringCentreList:ArrayList<caringCentre>){

        rvCaringCentre.layoutManager= LinearLayoutManager(this)
        val adapter5=adapterForCaringCentre(this,CaringCentreList)
        rvCaringCentre.adapter=adapter5
        adapter5.setOnClickListener(object :adapterForCaringCentre.onClickListener{
            override fun onClick(position: Int, model: caringCentre) {
                var uri :String = "tel:" + model.centrePhoneNumber.trim()
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse(uri))
                startActivity(intent)
            }


        })
    }


    fun userSignSuccess(){
        hideDialog()
        val intent =Intent(this,addCovidCentre::class.java)
        startActivityForResult(intent, CODE_FOR_INTENT_CARING_CENTRE )
    }
    companion object {
        val CODE_FOR_INTENT_CARING_CENTRE =145

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK && requestCode== CODE_FOR_INTENT_CARING_CENTRE){
           FirestoreClass().getCovidCaringList(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}