package com.example.android.graminkendra

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_asha.*
import kotlinx.android.synthetic.main.activity_addition_of_schemas.*
import kotlinx.android.synthetic.main.activity_asha_worker.*

class addAsha : baseactivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_asha)
        setActionBar()
        addAshaToList.setOnClickListener {
            createAsha()
        }
    }
    private fun setActionBar() {
        setSupportActionBar(toolbarAdditionOfAsha)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarAdditionOfAsha.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun createAsha(){
        val assignedArray=ArrayList<String>()
        assignedArray.add(getCurrentUserId())
        var asha1=asha(AshaName.text.toString(),wardAssigning.text.toString(),contactNumberOfAsha.text.toString(),AshaMail.text.toString())
FirestoreClass().createAsha(this,asha1)
    }
    fun AshaCreateSuccess(){
        setResult(Activity.RESULT_OK)
        finish()

    }




}