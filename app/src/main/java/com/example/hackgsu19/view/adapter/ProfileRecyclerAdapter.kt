package com.example.hackgsu19.view.adapter

import android.content.Context
import android.os.Build
import android.transition.Slide
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.R
import com.squareup.picasso.Picasso


class ProfileRecyclerAdapter: RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {

//    private val mDogList = DogModel.profileCardList
    private var mDogList = ArrayList<DogModel>()
    private lateinit var context: Context

    fun setDogs(dogs: ArrayList<DogModel>){
        mDogList = dogs
    }
    
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage: ImageView
        var cardName: TextView

        init {
            cardImage = itemView.findViewById(R.id.card_image)
            cardName = itemView.findViewById(R.id.card_name)
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
        var dog: DogModel = mDogList[i]
        viewHolder.cardName.text = dog.name

        //TODO: Get data from database and change placeholder image
        Picasso.with(context).load(R.drawable.bannanabread).placeholder(R.drawable.calendar_icon).into(viewHolder.cardImage)

        viewHolder.cardImage.setOnClickListener{
            context?.let {

                val popupView = LayoutInflater.from(context).inflate(R.layout.dog_popup, null)
                val popupWindow =
                    PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
                popupWindow.isFocusable = true

                val imageView: ImageView = popupView.findViewById<ImageView>(R.id.dog_image_expanded)
                imageView.setImageDrawable(viewHolder.cardImage.drawable)

                val cardName: TextView = popupView.findViewById<TextView>(R.id.dog_name_expanded)
                cardName.setText(dog.name)

                val orgName: TextView = popupView.findViewById<TextView>(R.id.dog_shelter_name)
//                orgName.setText(dog.org)


                // Set an elevation for the popup window
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    popupWindow.elevation = 10.0F
                }

                popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0)
            }
        }
    }


    override fun getItemCount(): Int {
        return mDogList.size
    }
}