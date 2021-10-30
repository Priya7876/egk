package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_phone_number.view.*
import kotlinx.android.synthetic.main.add_row_of_phone_list.view.*
import kotlinx.android.synthetic.main.row_design_for_covid_centre.view.*

class adapterForPhoneList
(var context: Context, var item:ArrayList<phoneFormat>)
: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var onClickListener1: onClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.add_row_of_phone_list,parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = item[position]
        if (holder is MyViewHolder) {
            holder.itemView.RFieldName.text=model.fieldName
            holder.itemView.positionInField.text=model.position

holder.itemView.RFContactNumber.text=model.contactOfField
            holder.itemView.callPhoneList.setOnClickListener {
                if(onClickListener1!=null){
                    onClickListener1!!.onClick(position,model)
                } }

        }



    }

    override fun getItemCount(): Int {
        return  item.size
    }
    inner   class MyViewHolder(view: View):RecyclerView.ViewHolder(view)
    interface onClickListener{
        fun onClick(position: Int,model:phoneFormat)


    }

    fun setOnClickListener(onClickListener:onClickListener){
        this.onClickListener1=onClickListener
    }
}