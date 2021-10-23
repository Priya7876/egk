package com.example.android.graminkendra

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_asha_worker.*
import kotlinx.android.synthetic.main.activity_vaccination_info.*

class vaccinationInfo : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccination_info)
        setActionBar()
        var currentUser=FirestoreClass().getCurrentUserId()

        VaccinationFab.setOnClickListener {
            if(currentUser.isNotEmpty()){
                showProgressDialog("Please wait")
                userSignSuccess()
            }
            else{
                showErrorSnackBar("only available to Panchayat's personnel")}
        }
        FirestoreClass().getVaccinationList(this)

    }
    private fun setActionBar() {
        setSupportActionBar(toolbarVaccinationInfo)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Vaccination Centre"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarVaccinationInfo.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun populateRecyclerView(VaccinationCentreList:ArrayList<vaccination>){

        rvVaccination.layoutManager= LinearLayoutManager(this)
        val adapter3=adapterForVaccinationq(this,VaccinationCentreList)
        rvVaccination.adapter=adapter3
        adapter3.setOnClickListener(object :adapterForVaccinationq.onClickListener{
            override fun onClick(position: Int, model: vaccination) {
                var uri :String = "tel:" + model.vacContactNumber.trim()
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse(uri))
                startActivity(intent)
            }


        })
    }
    fun userSignSuccess(){
        hideDialog()
        val intent =Intent(this,addVaccinationCentre::class.java)
        startActivityForResult(intent, CODE_FOR_INTENT_VACCINATION )
    }
    companion object {
        val CODE_FOR_INTENT_VACCINATION =140

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK && requestCode== CODE_FOR_INTENT_VACCINATION){
           FirestoreClass().getVaccinationList(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}