package com.example.form_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class RptBodyAdapter(private val bodyNames: ArrayList<RptBody>, private val listener: RptBodyAdapter.OnItemClickListener) : RecyclerView.Adapter<RptBodyAdapter.RptViewHolder>() {
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

    inner class RptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val btnBodyName: TextView = itemView.findViewById(R.id.rpt_body_btn)


        init {
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            //if (position!=RecyclerView.NO_POSITION)
            listener.bodyOnItemClick(position)
        }
    }

    interface OnItemClickListener{
        fun bodyOnItemClick(position: Int)

    }
}
