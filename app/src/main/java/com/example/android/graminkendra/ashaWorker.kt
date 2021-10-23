package com.example.android.graminkendra

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_asha_worker.*
import kotlinx.android.synthetic.main.activity_info_m.*

class ashaWorker : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asha_worker)
        setActionBar()
        var currentUser=FirestoreClass().getCurrentUserId()

        AshaFab.setOnClickListener {
            if(currentUser.isNotEmpty()){
                showProgressDialog("Please wait")
                userSignSuccess()
            }
            else{
                showErrorSnackBar("only available to Panchayat's personnel")}
        }
        FirestoreClass().getAshaList(this)


    }
   private fun setActionBar() {
        setSupportActionBar(toolbarAsha)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Details"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarAsha.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    fun populateRecyclerView(AshaList:ArrayList<asha>){

        rvAsha.layoutManager= LinearLayoutManager(this)
        val adapter3=adapterforAsha(this,AshaList)
        rvAsha.adapter=adapter3
        adapter3.setOnClickListener(object :adapterforAsha.onClickListener{


            override fun onClick(position: Int, model: asha) {
                var uri :String = "tel:" + model.phoneNumber.trim()
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse(uri))
                startActivity(intent)
            }

            override fun onClickMessage(position: Int, model: asha) {

                val emailSelectorIntent = Intent(Intent.ACTION_SENDTO)
                emailSelectorIntent.data = Uri.parse("mailto:"+ model.mailAsha)
                startActivity(emailSelectorIntent)

            }

        })
    }
    fun userSignSuccess(){
        hideDialog()
        val intent =Intent(this,addAsha::class.java)
        startActivityForResult(intent, CODE_FOR_INTENT_ASHA)
    }
    companion object {
        val CODE_FOR_INTENT_ASHA =14

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK && requestCode== CODE_FOR_INTENT_ASHA){
            FirestoreClass().getAshaList(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}