package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_xml_meet.view.*

class adapterForMeeting(var context: Context, var item:ArrayList<meeting>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickListener1: onClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_xml_meet, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = item[position]
        if (holder is MyViewHolder) {
            holder.itemView.meet_Name.text=model.eventName
            holder.itemView.date_time.text=model.dateTime
            holder.itemView.setOnClickListener {
                if(onClickListener1!=null){
                    onClickListener1!!.onClick(position,model)
                } }

        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
    interface onClickListener{
        fun onClick(position: Int,model:meeting)

}

    fun setOnClickListener(onClickListener:onClickListener){
        this.onClickListener1=onClickListener
    }


    }