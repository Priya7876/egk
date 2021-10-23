package com.example.android.graminkendra

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_asha.*
import kotlinx.android.synthetic.main.activity_add_vaccination_centre.*

class addVaccinationCentre : baseactivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vaccination_centre)
        setActionBar()
        addVaccinationCentreToList.setOnClickListener {
            createVaccinationCentre()
        }
    }
    private fun setActionBar() {
        setSupportActionBar(toolbarAdditionOfVaccinatonCentre)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarAdditionOfVaccinatonCentre.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun createVaccinationCentre(){
        val assignedArray=ArrayList<String>()
        assignedArray.add(getCurrentUserId())
        var vaccination1=vaccination(VcName.text.toString(),VcAddressAssigning.text.toString(),contactNumbeVc.text.toString())
        FirestoreClass().createVaccination(this,vaccination1)
    }
    fun VaccinationCreateSuccess(){
        setResult(Activity.RESULT_OK)
        finish()

    }


}