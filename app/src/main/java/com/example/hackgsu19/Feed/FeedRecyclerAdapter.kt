package com.example.hackgsu19.Feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.hackgsu19.R
import com.example.hackgsu19.Report


class FeedRecyclerAdapter: RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {


    private val mCardList = Report.dogCardList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var cardImage: ImageView
        var cardName: TextView
        var cardDetail: TextView
        var cardDate: TextView
        var cardOrg: TextView

        init {
            cardImage = itemView.findViewById(R.id.card_image)
            cardName = itemView.findViewById(R.id.card_name)
            cardDetail = itemView.findViewById(R.id.card_detail)
            cardDate = itemView.findViewById(R.id.card_date)
            cardOrg = itemView.findViewById(R.id.card_org)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var mReport: Report = mCardList[i]
        viewHolder.cardName.text = mReport.name
        viewHolder.cardDetail.text = mReport.detail
        viewHolder.cardDate.text = mReport.date
        viewHolder.cardOrg.text = mReport.org
        viewHolder.cardImage.setImageResource(mReport.image)

        viewHolder.cardImage.setOnClickListener{

        }
    }


    override fun getItemCount(): Int {
        return mCardList.size
    }
}