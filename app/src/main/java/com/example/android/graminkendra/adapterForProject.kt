package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.project_detail_row.view.*
import kotlinx.android.synthetic.main.row_design_asha.view.*

class adapterForProject(var context: Context, var item:ArrayList<project>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickListener1: onClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.project_detail_row,parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = item[position]
        if (holder is MyViewHolder) {
            holder.itemView.OnProjectName.text=model.projectName
            holder.itemView.progressBar.progress =100- model.progessDetail

            holder.itemView.editProjectDetails.setOnClickListener {
                if(onClickListener1!=null){
                    onClickListener1!!.onClick(position,model)
                } }
            holder.itemView.showDetails.setOnClickListener {
                if(onClickListener1!=null){
                    onClickListener1!!.onViewDetail(position,model)
                }
            }

        }



    }

    override fun getItemCount(): Int {
        return item.size
    }
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view)
    interface onClickListener{
        fun onClick(position: Int,model:project)
        fun onViewDetail(position: Int,model: project)

    }
    fun setOnClickListener(onClickListener: onClickListener){
        this.onClickListener1=onClickListener
    }
}