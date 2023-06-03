package com.example.form_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

data class RptHeaderAdapter(private val headerTitles : ArrayList<RptHeader>) : RecyclerView.Adapter<RptHeaderAdapter.RptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RptViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.report_header_item,
            parent,false)
        return RptViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return headerTitles.size
    }

    override fun onBindViewHolder(holder: RptViewHolder, position: Int) {
        val currentItem = headerTitles[position]
        holder.btnHeadingTitle.text = currentItem.headerBtnName
    }

    class RptViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val btnHeadingTitle : Button = itemView.findViewById(R.id.rpt_header_btn)
    }
}
