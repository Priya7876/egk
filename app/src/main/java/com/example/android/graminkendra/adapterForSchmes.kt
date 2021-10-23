package com.example.android.graminkendra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_design_schemes.view.*
import kotlinx.android.synthetic.main.rv_xml_meet.view.*

class adapterForSchmes(var context: Context, var item:ArrayList<scheme>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickListener1:onClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_design_schemes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = item[position]
        if (holder is MyViewHolder) {
           holder.itemView.scheme_Name.text=model.schemeName
            holder.itemView.due_date.text=model.dueDateScheme
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
        fun onClick(position: Int,model:scheme)

    }
    fun setOnClickListener(onClickListener: onClickListener){
        this.onClickListener1=onClickListener
    }
}