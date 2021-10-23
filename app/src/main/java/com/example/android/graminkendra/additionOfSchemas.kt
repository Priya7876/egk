package com.example.android.graminkendra

import android.app.Activity
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_addition_of_event.*
import kotlinx.android.synthetic.main.activity_addition_of_schemas.*
import kotlinx.android.synthetic.main.activity_details_of_schemes.*
import java.util.*
import kotlin.collections.ArrayList

class additionOfSchemas : baseactivity() {
    private var mSelectedDueDateMilliSecond :Long =0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition_of_schemas)
        setActionBar()
        addSchemeToList.setOnClickListener {
            createScheme()
        }
        selectDateScheme.setOnClickListener {
            showDatePicker()
        }
        if(mSelectedDueDateMilliSecond>0) {
            var simpleDateFormat =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val  selectDate =simpleDateFormat.format(Date(mSelectedDueDateMilliSecond))
            dueDateOfScheme.text=selectDate
        }
    }
    fun setActionBar() {
        setSupportActionBar(toolbarAdditionOfScheme)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add Scheme"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarAdditionOfScheme.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun createScheme(){
        val assignedArray=ArrayList<String>()
        assignedArray.add(getCurrentUserId())
var scheme=scheme(schemeName.text.toString(),dueDateScheme.text.toString(),linkForthescheme.text.toString(),descriptive.text.toString(),stepsOfapplication.text.toString())
        FirestoreClass().createScheme(this,scheme)
    }
    fun SchemeCreateSuccess(){
        setResult(Activity.RESULT_OK)
        finish()

    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDatePicker(){
        val c = Calendar.getInstance()
        val year =c.get(Calendar.YEAR)
        val month =c.get(Calendar.MONTH)
        val day =c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        {
                view, year, month, dayOfMonth ->
            val  SdayOfMonth =if(dayOfMonth<10)  "0${dayOfMonth}" else  "${dayOfMonth}"
            val SMonthOfYear =if((month+1)<10) "0${month+1}" else "${month+1}"
            val selectedDate ="$SdayOfMonth/$SMonthOfYear/$year"
            dueDateScheme.text = selectedDate
            val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate =sdf.parse(selectedDate)
             mSelectedDueDateMilliSecond = theDate!!.time
        },year,month,day

        )
        dpd.show()

    }
}