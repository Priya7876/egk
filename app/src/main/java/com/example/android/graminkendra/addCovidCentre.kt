package com.example.android.graminkendra

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_covid_centre.*
import kotlinx.android.synthetic.main.activity_add_vaccination_centre.*

class addCovidCentre : baseactivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_covid_centre)
        setActionBar()
        addCovidCentreToList.setOnClickListener {
            createCovidCaringCentre()
        }
    }
    private fun setActionBar() {
        setSupportActionBar(toolbarAdditionOfCovidCentre)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarAdditionOfCovidCentre.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun createCovidCaringCentre(){
        val assignedArray=ArrayList<String>()
        assignedArray.add(getCurrentUserId())
        var covidCentre1=caringCentre(CcName.text.toString(),CcAddressAssigning.text.toString(),contactNumbeCc.text.toString())
        FirestoreClass().createCovidCaringCentre(this,covidCentre1)
    }

    fun CovidCaringCreateSuccess(){
        setResult(Activity.RESULT_OK)
        finish()

    }




}