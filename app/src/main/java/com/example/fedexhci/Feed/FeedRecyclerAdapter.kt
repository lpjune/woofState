package com.example.fedexhci.Feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fedexhci.R
import com.example.fedexhci.Report


class FeedRecyclerAdapter: RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {

    private val mReportList = Report.managerReportList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemDate: TextView
        var itemAuthor: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            itemDate = itemView.findViewById(R.id.item_date)
            itemAuthor = itemView.findViewById(R.id.item_author)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var mReport: Report = mReportList[i]
        viewHolder.itemTitle.text = mReport.title
        viewHolder.itemDetail.text = mReport.detail
        viewHolder.itemDate.text = mReport.date
        viewHolder.itemAuthor.text = mReport.author
        viewHolder.itemImage.setImageResource(mReport.image)
    }

    override fun getItemCount(): Int {
        return mReportList.size
    }
}