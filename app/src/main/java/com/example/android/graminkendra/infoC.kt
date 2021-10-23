package com.example.android.graminkendra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_info_c.*

class infoC : baseactivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_c)
        setActionBar()
        addComplaintFab.setOnClickListener {
            var intent = Intent(this,addComplaint::class.java)
            startActivityForResult(intent, COMPLAINT)
        }
         FirestoreClass().getBoardList(this)
    }
    fun setActionBar() {
        setSupportActionBar(toolbarComplaint)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Complaints"

            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
       toolbarComplaint.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    fun populateRecyclerView(ComplaintList :ArrayList<complaint>){

   rvComplaint.layoutManager=LinearLayoutManager(this)
val adapter1=adapterForComplaint(this,ComplaintList)
        rvComplaint.adapter=adapter1
        adapter1.setOnClickListener(object:adapterForComplaint.onClickListener{
            override fun onClick(position: Int, model: complaint) {
                val intent =Intent(this@infoC,complaintDetail::class.java)
                intent.putExtra(EXTRA_INFO,model)
                startActivity(intent)
            }

        })


}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK && requestCode== COMPLAINT){
            FirestoreClass().getBoardList(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
companion object{
  const  val COMPLAINT =89
    const val EXTRA_INFO="sending detail of complaint"
}}