package com.example.hackgsu19.Profile

import android.content.Context
import android.os.Build
import android.transition.Slide
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.R
import com.example.hackgsu19.Report


class ProfileRecyclerAdapter: RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {

    private val mProfileList = Report.profileCardList
    private lateinit var context: Context

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

    fun setContext(context: Context?){
        context?.let { this.context = context }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var mReport: Report = mProfileList[i]
        viewHolder.cardName.text = mReport.name
        viewHolder.cardDetail.text = mReport.detail
        viewHolder.cardDate.text = mReport.date
        viewHolder.cardOrg.text = mReport.org
        viewHolder.cardImage.setImageResource(mReport.image)

        viewHolder.cardImage.setOnClickListener{
            context?.let {

                val popupView = LayoutInflater.from(context).inflate(R.layout.dog_popup, null)
                val popupWindow =
                    PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
                popupWindow.isFocusable = true

                val imageView: ImageView = popupView.findViewById<ImageView>(R.id.dog_image_expanded)
                imageView.setImageResource(mReport.image)

                val cardName: TextView = popupView.findViewById<TextView>(R.id.dog_name_expanded)
                cardName.setText(mReport.name)

                val orgName: TextView = popupView.findViewById<TextView>(R.id.dog_shelter_name)
                orgName.setText(mReport.org)


                // Set an elevation for the popup window
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    popupWindow.elevation = 10.0F
                }


                // If API level 23 or higher then execute the code
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    // Create a new slide animation for popup window enter transition
                    val slideIn = Slide()
                    slideIn.slideEdge = Gravity.TOP
                    popupWindow.enterTransition = slideIn

                    // Slide animation for popup window exit transition
                    val slideOut = Slide()
                    slideOut.slideEdge = Gravity.RIGHT
                    popupWindow.exitTransition = slideOut

                }

                popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0)
            }
        }
    }


    override fun getItemCount(): Int {
        return mProfileList.size
    }
}