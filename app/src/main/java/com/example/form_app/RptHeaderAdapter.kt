package com.example.form_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

data class RptHeaderAdapter(private val headerTitles : ArrayList<RptHeader>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RptHeaderAdapter.RptViewHolder>() {

    private var headSelected : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RptViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.report_header_item,
            parent,false)
        return RptViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return headerTitles.size
    }

    //https://stackoverflow.com/questions/40603251/changing-colors-to-recyclerview-items
    //figure out what this does
    override fun onBindViewHolder(holder: RptViewHolder, position: Int) {
        val currentItem = headerTitles[position]
        holder.btnHeadingTitle.text = currentItem.headerBtnName
        holder.bind(currentItem, position)
    }

    inner class RptViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val btnHeadingTitle : TextView = itemView.findViewById(R.id.rpt_header_btn)

        init {
            itemView.setOnClickListener (this)
        }


        fun bind (item: RptHeader, position: Int) {

            btnHeadingTitle.text = item.headerBtnName

            if (position == headSelected) {

                btnHeadingTitle.setBackgroundColor(ContextCompat.getColor(btnHeadingTitle.context, R.color.light_blue))
                btnHeadingTitle.setTextColor(ContextCompat.getColor(btnHeadingTitle.context, R.color.white))
            }
            else
            {
                btnHeadingTitle.background = ContextCompat.getDrawable(btnHeadingTitle.context, R.drawable.report_header_border)
                btnHeadingTitle.setTextColor(ContextCompat.getColor(btnHeadingTitle.context, R.color.light_blue))
            }
        }

        override fun onClick(v: View?) {
            val headPrv = headSelected
            headSelected = adapterPosition

            notifyItemChanged(headSelected)
            notifyItemChanged(headPrv)

            val position: Int = adapterPosition
            //if (position!=RecyclerView.NO_POSITION)
            listener.headerOnItemClick(position)
        }
    }

    interface OnItemClickListener{
        fun headerOnItemClick(position: Int)

    }
}
