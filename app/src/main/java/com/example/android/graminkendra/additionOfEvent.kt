package com.example.android.graminkendra

import android.app.Activity
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_add_complaint.*
import kotlinx.android.synthetic.main.activity_addition_of_event.*
import kotlinx.android.synthetic.main.activity_info_c.*
import java.util.*
import kotlin.collections.ArrayList

class additionOfEvent :baseactivity() {
    private var mSelectedDueDateMilliSecond :Long =0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition_of_event)
        setActionBar()
        addEventToList.setOnClickListener {
            createEvent()
        }
        selectAdueDateE.setOnClickListener {
            showDatePicker()
        }
        if(mSelectedDueDateMilliSecond>0) {
            var simpleDateFormat =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val  selectDate =simpleDateFormat.format(Date(mSelectedDueDateMilliSecond))
            dateOfEvent.text=selectDate
        }

    }
    fun setActionBar() {
        setSupportActionBar(toolbarAdditionOfEvent)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add Event"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarAdditionOfEvent.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun createEvent(){
        val assignedArray=ArrayList<String>()
        assignedArray.add(getCurrentUserId())
        var event=meeting(eventName.text.toString(),dateOfEvent.text.toString(),descriptionOfMeet.text.toString(),assignedArray)
      FirestoreClass().createMeet(this,event)

    }
    fun MeetCreateSuccess(){
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
            dateOfEvent.text = selectedDate
            val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate =sdf.parse(selectedDate)
            mSelectedDueDateMilliSecond=theDate!!.time},year,month,day

        )
        dpd.show()

    }
}