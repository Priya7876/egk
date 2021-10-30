package com.example.android.graminkendra

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_info_c.*
import kotlinx.android.synthetic.main.activity_info_m.*
import kotlinx.android.synthetic.main.activity_phone_number_list.*
import kotlinx.android.synthetic.main.activity_vaccination_info.*

class phoneNumberList : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number_list)
        setActionBar()
        var currentUser=FirestoreClass().getCurrentUserId()

        addPhoneFab.setOnClickListener {
            if(currentUser.isNotEmpty()){
                showProgressDialog("Please wait")
                userSignSuccess()
            }
            else{
                showErrorSnackBar("only available to Panchayat's personnel")}
        }
        FirestoreClass().getPhoneList(this)





    }
  private  fun setActionBar() {
        setSupportActionBar(toolbarTelephony)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Diary Of Numbers"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarTelephony.setNavigationOnClickListener {
            onBackPressed()
        }
    }
  fun populateRecyclerView(PhoneList :ArrayList<phoneFormat>){

        rvPhoneList.layoutManager= LinearLayoutManager(this)
        val adapter2=adapterForPhoneList(this,PhoneList)
      rvPhoneList.adapter=adapter2
        adapter2.setOnClickListener(object:adapterForPhoneList.onClickListener{
            override fun onClick(position: Int, model: phoneFormat) {
                var uri :String = "tel:" + model.contactOfField.trim()
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse(uri))
                startActivity(intent)
            }


        })


    }



    fun userSignSuccess(){
        hideDialog()
        val intent =Intent(this,AddPhoneNumber::class.java)
        startActivityForResult(intent, CODE_FOR_INTENT_PHONEnUMBER)
    }
    companion object {
        val CODE_FOR_INTENT_PHONEnUMBER =146

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK && requestCode== CODE_FOR_INTENT_PHONEnUMBER){
           FirestoreClass().getPhoneList(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}






