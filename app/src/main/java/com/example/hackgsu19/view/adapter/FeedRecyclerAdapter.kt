package com.example.hackgsu19.view.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.transition.Slide
import android.view.*
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class FeedRecyclerAdapter: RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var dogList: ArrayList<DogModel> = ArrayList<DogModel>()

//    private val mCardList = Report.dogCardList

    fun setDogs (dogList: ArrayList<DogModel>){
        this.dogList = dogList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage: ImageView
        var cardName: TextView

        init {
            cardImage = itemView.findViewById(R.id.card_image)
            cardName = itemView.findViewById(R.id.card_name)
            cardName.elevation = 10f
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
        var dog: DogModel = dogList[i]

        viewHolder.cardName.text = dog.name

        if (dog.image == null){
            Picasso.with(context)
                .load(dog.imageUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.dogplaceholder)
                .into(viewHolder.cardImage)

            viewHolder.cardImage.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryLight))
        } else {
            viewHolder.cardImage.setImageDrawable(dog.image)
        }

        viewHolder.cardImage.setOnClickListener{
            context.let {

                val popupView = LayoutInflater.from(context).inflate(R.layout.dog_popup, null)
                val popupWindow =
                    PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                popupWindow.isFocusable = true

                val imageView: ImageView = popupView.findViewById(R.id.dog_image_expanded)
                imageView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorSecondaryLight))
//                To load full image
//                Picasso.with(context)
//                    .load(dog.imageUrl)
//                    .fit()
//                    .centerInside()
//                    .placeholder(R.drawable.dogplaceholder)
//                    .into(imageView)
                imageView.setImageDrawable(viewHolder.cardImage.drawable)

                if (imageView.drawable == null){
                    viewHolder.cardImage.setBackgroundColor(Color.CYAN)
                    viewHolder.cardImage.setImageDrawable(dog.image)
                }

                val cardName: TextView = popupView.findViewById(R.id.dog_name_expanded)
                cardName.setText(dog.name)

                val orgName: TextView = popupView.findViewById(R.id.dog_shelter_name)
//                orgName.setText(dog.organizationId)

                val breeds: TextView = popupView.findViewById(R.id.dog_breeds)
                breeds.setText(dog.breeds)

                val quickInfo: TextView = popupView.findViewById(R.id.dog_quickinfo)
                quickInfo.setText(dog.gender?.plus(" ⋅ ").plus(dog.age).plus(" ⋅ ").plus(dog.size))

                // Set an elevation for the popup window
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    popupWindow.elevation = 10.0F
                }

                popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0)


                val fab = popupView.findViewById<FloatingActionButton>(R.id.addFab)
                fab.setOnClickListener {
//                    popupWindow.dismiss()
//                    openWalkADog(context, dog.name )
                    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val myRef: DatabaseReference = database.getReference("users")

                    myRef.setValue("Testing")
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return dogList.size
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