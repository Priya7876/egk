package com.example.android.graminkendra

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_addition_of_schemas.*
import kotlinx.android.synthetic.main.activity_details_of_schemes.*


class detailsOfSchemes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_of_schemes)
        setActionBar()
       if(intent.hasExtra(infoS.CODE_FOR_SENDING_DETAIL_SCHEME)) {
           var schemeDetail =
               intent.getParcelableExtra<scheme>(infoS.CODE_FOR_SENDING_DETAIL_SCHEME) as scheme
           schemeNameDetaul.text = schemeDetail.schemeName
           dueDateOfScheme.text = schemeDetail.dueDateScheme
           if (schemeDetail.hyperLink.toString() == null) {
               linkOfScheme.visibility = View.GONE
           } else {
               var link=schemeDetail.hyperLink
               linkOfScheme.text= link
               linkOfScheme.setOnClickListener {
               val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
               startActivity(browserIntent)
           }}
           descriptionOfApplication.text=schemeDetail.description
           stepsForapplication.text=schemeDetail.steps
       }
    }
    private fun setActionBar() {
        setSupportActionBar(toolBarSchemeDetail)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Details"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolBarSchemeDetail.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}