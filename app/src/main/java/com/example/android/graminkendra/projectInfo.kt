package com.example.android.graminkendra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_on_going_project.*
import kotlinx.android.synthetic.main.activity_info_m.*
import kotlinx.android.synthetic.main.activity_info_s.*
import kotlinx.android.synthetic.main.activity_project_info.*
import kotlinx.android.synthetic.main.project_detail_row.*

class projectInfo : baseactivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_info)
        setActionBar()
        var currentUser=FirestoreClass().getCurrentUserId()

        ProjectFab.setOnClickListener {
            if(currentUser.isNotEmpty()){
                showProgressDialog("Please wait")
                userSignSuccess()
            }
            else{
                showErrorSnackBar("only available to Panchayat's personnel")}
        }

        FirestoreClass().getProjectList(this)




    }
    private fun setActionBar() {
        setSupportActionBar(toolbarProjectInfo)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Projects and Progress"
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbarProjectInfo .setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun userSignSuccess(){
        hideDialog()
        val intent = Intent(this,addOnGoingProject::class.java)
        startActivityForResult(intent, CODE_FOR_INTENT_ADD_PROJECTS)
    }
    companion object {
        val CODE_FOR_INTENT_ADD_PROJECTS =10
        val CODE_FOR_EDIT=11
        const val EXTRA_INFO_PROJECT_EDIT="sending detail of project"
        const val EXTRA_INFO_PROJECT_DETAIL="sending detail of project details"

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK && requestCode== CODE_FOR_INTENT_ADD_PROJECTS){
           FirestoreClass().getProjectList(this)
        }
        if(resultCode== RESULT_OK && requestCode== CODE_FOR_EDIT){
            FirestoreClass().getProjectList(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun populateRecyclerView(ProjectList:ArrayList<project>){
       rvProjectList.layoutManager= LinearLayoutManager(this)
        val adapter1 =adapterForProject(this,ProjectList)
        rvProjectList.adapter=adapter1
        adapter1.setOnClickListener(object :adapterForProject.onClickListener{
            override fun onClick(position: Int, model: project) {
                var currentUser=FirestoreClass().getCurrentUserId()
                if(currentUser.isNotEmpty()){

                    val intent =Intent(this@projectInfo,editDetail::class.java)
                    intent.putExtra(EXTRA_INFO_PROJECT_EDIT,model)
                    startActivityForResult(intent, CODE_FOR_EDIT)
                    }
                    else{

                        showErrorSnackBar("only available to Panchayat's personnel") }

            }

            override fun onViewDetail(position: Int, model: project) {
                val intent =Intent(this@projectInfo,DetailOfProject::class.java)
                intent.putExtra(EXTRA_INFO_PROJECT_DETAIL,model)
                startActivity(intent)
            }


        })

    }

}