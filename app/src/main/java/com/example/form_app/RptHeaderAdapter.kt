package com.example.form_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class RptHeaderAdapter(private val headerTitles : ArrayList<RptHeader>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RptHeaderAdapter.RptViewHolder>() {

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

    inner class RptViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val btnHeadingTitle : TextView = itemView.findViewById(R.id.rpt_header_btn)

        init {
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            //if (position!=RecyclerView.NO_POSITION)
            Log.i("console", "please:")
            listener.onItemClick(position)
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)

    }
}
