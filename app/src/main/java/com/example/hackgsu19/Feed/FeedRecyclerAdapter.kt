package com.example.hackgsu19.Feed

import android.content.Context
import android.os.Build
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.transition.Visibility
import android.view.*
import android.widget.*
import com.example.hackgsu19.R
import com.example.hackgsu19.Report


class FeedRecyclerAdapter: RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {
    private lateinit var context: Context

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

    fun setContext(context: Context?){
        context?.let { this.context = context }
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
            context?.let {
                // Initialize a new layout inflater instance
                val inflater:LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                // Inflate a custom view using layout inflater
                val view = inflater.inflate(R.layout.badge_popup,null)


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


                val fab = popupView.findViewById<FloatingActionButton>(R.id.fab)
                fab.setOnClickListener {
                    popupWindow.dismiss()
                    openWalkADog(context, mReport.name )
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return mCardList.size
    }

    fun openWalkADog(context: Context?, badgeTitle:String){
        // Initialize a new layout inflater instance

        val popupView = LayoutInflater.from(context).inflate(R.layout.walk_a_dog_popup, null)

        val badgeTitleId = popupView.findViewById<TextView>(R.id.badge_title_id)
        badgeTitleId.setText("Schedule a walk with ".plus(badgeTitle))

        val fromButton = popupView.findViewById<Button>(R.id.from)
        val fromDateTextView = popupView.findViewById<TextView>(R.id.from_date_text_view)
        val fromTimeTextView = popupView.findViewById<TextView>(R.id.from_time_text_view)
        val fromDatePicker = popupView.findViewById<DatePicker>(R.id.date_picker_from)
        val fromTimePicker = popupView.findViewById<TimePicker>(R.id.time_picker_from)


        val toButton = popupView.findViewById<TextView>(R.id.to)
        val toDateTextView = popupView.findViewById<TextView>(R.id.to_date_text_view)
        val toTimeTextView = popupView.findViewById<TextView>(R.id.to_time_text_view)
        val toDatePicker = popupView.findViewById<DatePicker>(R.id.date_picker_to)
        val toTimePicker = popupView.findViewById<TimePicker>(R.id.time_picker_to)
        val submitButton = popupView.findViewById<Button>(R.id.submit)

        fromDatePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            fromDateTextView.setText(monthOfYear.toString() + "/"+ dayOfMonth+"/"+year)
        }

        fromTimePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            fromTimeTextView.setText(hourOfDay.toString().plus(':').plus(minute))
        }

        fromButton.setOnClickListener {
            if (fromDatePicker.visibility == View.GONE){
                fromDatePicker.visibility = View.VISIBLE
                fromTimePicker.visibility = View.VISIBLE
            } else {
                fromDatePicker.visibility = View.GONE
                fromTimePicker.visibility = View.GONE
            }
        }

        toDatePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            toDateTextView.setText(monthOfYear.toString() + "/"+ dayOfMonth+"/"+year)
        }

        toTimePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            toTimeTextView.setText(hourOfDay.toString().plus(':').plus(minute))
        }

        toButton.setOnClickListener {
            if (toDatePicker.visibility == View.GONE){
                toDatePicker.visibility = View.VISIBLE
                toTimePicker.visibility = View.VISIBLE
            } else {
                toDatePicker.visibility = View.GONE
                toTimePicker.visibility = View.GONE
            }
        }


        val popupWindow =
            PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        popupWindow.isFocusable = true

        submitButton.setOnClickListener {
            popupWindow.dismiss()
        }



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