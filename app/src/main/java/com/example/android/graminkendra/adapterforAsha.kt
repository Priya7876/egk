package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_design_asha.view.*
import kotlinx.android.synthetic.main.rv_xml_meet.view.*

class adapterforAsha(var context: Context, var item:ArrayList<asha>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickListener1: onClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_design_asha,parent,false))
     }

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         val model = item[position]
         if (holder is MyViewHolder) {
             holder.itemView.ashaName.text=model.nameOfAsha
             holder.itemView.wardNo.text=model.wardDetail
             holder.itemView.ContactNum.text=model.phoneNumber
             holder.itemView.call.setOnClickListener {
                 if(onClickListener1!=null){
                     onClickListener1!!.onClick(position,model)
                 } }
             holder.itemView.message.setOnClickListener {
                 if(onClickListener1!=null){
                     onClickListener1!!.onClickMessage(position,model)
                 }
             }

         }
     }

     override fun getItemCount(): Int {
       return item.size
     }
  inner   class MyViewHolder(view: View):RecyclerView.ViewHolder(view)
    interface onClickListener{
        fun onClick(position: Int,model:asha)
        fun onClickMessage(position: Int,model: asha)

    }

    fun setOnClickListener(onClickListener:onClickListener){
        this.onClickListener1=onClickListener
    }
 }