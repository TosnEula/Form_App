package com.example.form_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

data class RptBodyAdapter(private val bodyNames: ArrayList<RptBody>) : RecyclerView.Adapter<RptBodyAdapter.RptViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RptViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.report_body_item,
            parent, false
        )
        return RptViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bodyNames.size
    }

    override fun onBindViewHolder(holder: RptViewHolder, position: Int) {
        val currentItem = bodyNames[position]
        holder.btnBodyName.text = currentItem.bodyBtnName
    }

    class RptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnBodyName: Button = itemView.findViewById(R.id.rpt_body_btn)
    }
}
