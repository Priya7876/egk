package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_design_asha.view.*
import kotlinx.android.synthetic.main.row_design_vaccination_centres.view.*

class adapterForVaccinationq(var context: Context, var item:ArrayList<vaccination>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var onClickListener2: onClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_design_vaccination_centres,parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = item[position]
        if (holder is MyViewHolder) {
           holder.itemView.vCentreName.text=model.vacCentreName
            holder.itemView.vCaddress.text=model.vacAddress
            holder.itemView.VcContactNumber.text=model.vacContactNumber

            holder.itemView.callVaccinationCentre.setOnClickListener {
                if(onClickListener2!=null){
                    onClickListener2!!.onClick(position,model)
                } }

        }

    }

    override fun getItemCount(): Int {
    return   item.size
    }
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view)
    interface onClickListener{
        fun onClick(position: Int,model:vaccination)
    }

    fun setOnClickListener(onClickListener:onClickListener){
        this.onClickListener2=onClickListener
    }

}