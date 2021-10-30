package com.example.android.graminkendra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_complaint_detail.*
import kotlinx.android.synthetic.main.activity_detail_of_project.*

class DetailOfProject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_of_project)
        setActionBar()
        if(intent.hasExtra(projectInfo.EXTRA_INFO_PROJECT_DETAIL)){
            val projectDetails = intent.getParcelableExtra<project>(projectInfo.EXTRA_INFO_PROJECT_DETAIL) as project
            Glide
                .with(this)
                .load(projectDetails.imagePriject)
                .centerCrop()
                .placeholder(R.drawable.placeholder).dontAnimate()
                .into(imageViewForDetailProject)

           ProjecNameOngoing.text=projectDetails.projectName
            descriptionOfProject.text=projectDetails.projectDescription
            startingDate.text=projectDetails.startingDate
            workers.text=projectDetails.noOfWorkers
            statusOfCompletion.text=projectDetails.status
        }
    }
    private fun setActionBar() {
        setSupportActionBar(toolProjectDetail)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Details"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolProjectDetail.setNavigationOnClickListener {
            onBackPressed()
        }
    }






}