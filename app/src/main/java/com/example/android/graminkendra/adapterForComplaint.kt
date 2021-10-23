package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_xml_complaint.view.*

class adapterForComplaint(var context: Context, var item:ArrayList<complaint>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickListener1:onClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_xml_complaint,parent,false))

    }
    override fun getItemCount(): Int {
       return item.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model=item[position]
        if(holder is MyViewHolder){
            Glide
                .with(context)
                .load(model.image)
                .centerCrop()
                .placeholder(R.drawable.placeholder).dontAnimate()
                .into(holder.itemView.imageOfRecycleView)
            holder.itemView.Board_Name.text=model.title
            holder.itemView.cREATER_Name.text=model.description
            holder.itemView.setOnClickListener {
                if(onClickListener1!=null){
                    onClickListener1!!.onClick(position,model)
                } }


    }}
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view)
    interface onClickListener{
        fun onClick(position: Int,model:complaint)

    }
    fun setOnClickListener(onClickListener: onClickListener){
        this.onClickListener1=onClickListener
    }

}
