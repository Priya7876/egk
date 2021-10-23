package com.example.android.graminkendra

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.android.graminkendra.infoC.Companion.EXTRA_INFO
import kotlinx.android.synthetic.main.activity_complaint_detail.*
import kotlinx.android.synthetic.main.row_xml_complaint.view.*

class complaintDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_detail)
        setActionBar()
        if(intent.hasExtra(EXTRA_INFO)){
            val complaintDetails = intent.getParcelableExtra<complaint>(EXTRA_INFO) as complaint
            Glide
                .with(this)
                .load(complaintDetails.image)
                .centerCrop()
                .placeholder(R.drawable.placeholder).dontAnimate()
                .into(imageViewForDetail)

            titleOfCom.text=complaintDetails.title
            deScriptionCom.text=complaintDetails.description
        }
        }


    private fun setActionBar() {
        setSupportActionBar(toolBarComplaintDetail)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Details"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolBarComplaintDetail.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}