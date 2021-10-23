package com.example.android.graminkendra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info_m.*
import kotlinx.android.synthetic.main.activity_symtoms_and_instructions.*

class symtomsAndInstructions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symtoms_and_instructions)
        setActionBar()
        onClickListeners()
    }
    private fun setActionBar() {
        setSupportActionBar(toolbarSymptomsAndInstruction)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Symptoms and Precautions"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarSymptomsAndInstruction.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun onClickListeners(){
        SymptomButton.setOnClickListener {
            val intent= Intent(this,commonSymtoms::class.java)
            startActivity(intent)
        }
        PreviousButton.setOnClickListener {
            val intent= Intent(this,ImporantInstructions::class.java)
            startActivity(intent)
        }
    }
}