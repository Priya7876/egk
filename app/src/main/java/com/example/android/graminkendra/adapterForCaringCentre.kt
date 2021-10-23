package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_design_for_covid_centre.view.*
import kotlinx.android.synthetic.main.row_design_vaccination_centres.view.*

class adapterForCaringCentre(var context: Context, var item:ArrayList<caringCentre>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var onClickListener2:onClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_design_for_covid_centre,parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = item[position]
        if (holder is MyViewHolder) {
            holder.itemView.CCentreName.text=model.caringCentreName
            holder.itemView.cCaddress.text=model.caringCentreAddress
            holder.itemView.CcContactNumber.text=model.centrePhoneNumber

            holder.itemView.callCovidCentre.setOnClickListener {
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
        fun onClick(position: Int,model:caringCentre)
    }

    fun setOnClickListener(onClickListener:onClickListener){
        this.onClickListener2=onClickListener
    }
}