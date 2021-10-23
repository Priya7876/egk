package com.example.android.graminkendra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_common_symtoms.*
import kotlinx.android.synthetic.main.activity_symtoms_and_instructions.*

class commonSymtoms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_symtoms)
        setActionBar()
    }
    private fun setActionBar() {
        setSupportActionBar(toolBarCommonSymtomsDetail)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Common Symptoms"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolBarCommonSymtomsDetail.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}