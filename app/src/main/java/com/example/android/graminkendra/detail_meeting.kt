package com.example.android.graminkendra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_complaint_detail.*
import kotlinx.android.synthetic.main.activity_detail_meeting.*
import kotlinx.android.synthetic.main.activity_detail_meeting.descriptionOFmEET
import kotlinx.android.synthetic.main.activity_detail_meeting.dueDate
import kotlinx.android.synthetic.main.activity_detail_meeting.titleOfMeet
import kotlinx.android.synthetic.main.detail_meeting.*

class detail_meeting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_meeting)
        setActionBar()
        if(intent.hasExtra(infoM.EVENT_SENDING)){
            val meetDetails = intent.getParcelableExtra<meeting>(infoM.EVENT_SENDING) as meeting
            titleOfMeet.text=meetDetails.eventName
            dueDate.text=meetDetails.dateTime
            descriptionOFmEET.text=meetDetails.meetDes
        }

    }
    private fun setActionBar() {
        setSupportActionBar(toolBarMeetDetail)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Details"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolBarMeetDetail.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}