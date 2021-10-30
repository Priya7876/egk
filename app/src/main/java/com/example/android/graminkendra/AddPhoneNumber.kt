package com.example.android.graminkendra

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_phone_number.*
import kotlinx.android.synthetic.main.activity_add_vaccination_centre.*

class AddPhoneNumber : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_phone_number)
        setActionBar()
        addPhoneToList.setOnClickListener {
            CreatephoneList()
        }
    }
    private fun setActionBar() {
        setSupportActionBar(toolbarAdditionOfPhoneList)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarAdditionOfPhoneList.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun CreatephoneList(){
        val assignedArray=ArrayList<String>()
        assignedArray.add(getCurrentUserId())
        var phone1=phoneFormat(FieldName.text.toString(),PositionName.text.toString(),contactNoField.text.toString())
        FirestoreClass().createPhoneNumber(this,phone1)
    }

    fun PhoneListCreated(){
        setResult(Activity.RESULT_OK)
        finish()

    }

}