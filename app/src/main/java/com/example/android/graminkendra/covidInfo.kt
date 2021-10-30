package com.example.android.graminkendra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_complaint.*
import kotlinx.android.synthetic.main.activity_covid_info.*

class covidInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_info)
        setActionBar()
        onClickListenersOfCovid()
    }
    fun setActionBar() {
        setSupportActionBar(toolbarCovid)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Covid-19"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarCovid.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun onClickListenersOfCovid(){
        AshaWorker.setOnClickListener {
            val intent = Intent(this@covidInfo,com.example.android.graminkendra.ashaWorker::class.java)
            startActivity(intent)
        }
        vaccinationCentre.setOnClickListener {
            val intent1 =Intent(this@covidInfo,vaccinationInfo::class.java)
            startActivity(intent1)
        }
        CaringCentre.setOnClickListener {
            val intent1 =Intent(this@covidInfo,covidCentreInfo::class.java)
            startActivity(intent1)
        }
        precaustion.setOnClickListener {
            val intent1 =Intent(this@covidInfo,symtomsAndInstructions::class.java)
            startActivity(intent1)
        }

    }

}