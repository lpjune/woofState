package com.example.hackgsu19.view.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.*
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.EmailService
import com.example.hackgsu19.OrgModel
import com.example.hackgsu19.R
import com.example.hackgsu19.api.DogClient
import com.example.hackgsu19.api.OrgClient
import com.facebook.Profile
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.loopj.android.http.JsonHttpResponseHandler
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class FeedRecyclerAdapter: RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var dogList: ArrayList<DogModel> = ArrayList<DogModel>()
    private val database = FirebaseDatabase.getInstance().reference
    private val profile: Profile = Profile.getCurrentProfile()

//    private val mCardList = Report.dogCardList

    fun setDogs (dogList: ArrayList<DogModel>){
        this.dogList = dogList
    }

    fun setDogsAtI (dog: DogModel, i: Int){
        this.dogList[i] = dog
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

        if (dog.imageUrl == null){
            Picasso.with(context)
                .load(R.drawable.ic_image_grey_24dp)
                .fit()
                .centerCrop()
                .into(viewHolder.cardImage)
            viewHolder.cardImage.setBackgroundColor(Color.GRAY)
        } else {
            Picasso.with(context)
                .load(dog.imageUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.dogplaceholder)
                .into(viewHolder.cardImage)
            viewHolder.cardImage.setBackgroundColor(ContextCompat.getColor(context,R.color.colorSecondaryLight))
        }

        viewHolder.cardImage.setOnClickListener{
            context.let {

                val popupView = LayoutInflater.from(context).inflate(R.layout.dog_popup, null)
                val popupWindow =
                    PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                popupWindow.isFocusable = true

                val imageView: ImageView = popupView.findViewById(R.id.dog_image_expanded)
                imageView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorSecondaryLight))
                imageView.setImageDrawable(viewHolder.cardImage.drawable)

                if (imageView.drawable == null){
                    viewHolder.cardImage.setBackgroundColor(Color.CYAN)
                    viewHolder.cardImage.setImageDrawable(dog.image)
                }

                val likeButton: ImageView = popupView.findViewById(R.id.likeButton)
                likeButton.setOnClickListener {
                    database.child("users").child(profile.id).child("likes").child(dog.id.toString()).addListenerForSingleValueEvent(object: ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (dataSnapshot.value == true) {
                                print("DOG IS UNLIKED")
                                database.child("users").child(profile.id).child("likes").child(dog.id.toString()).setValue(false)
                                likeButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_white_24dp))
                            } else {
                                print("DOG IS LIKED")
                                database.child("users").child(profile.id).child("likes").child(dog.id.toString()).setValue(true)
                                likeButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_white_24dp))
                            }
                        }
                        override fun onCancelled(databaseError: DatabaseError) {

                        }
                    })
                    database.child("dogs").child(dog.id.toString()).setValue(dog)
                }

                val cardName: TextView = popupView.findViewById(R.id.dog_name_expanded)
                cardName.setText(dog.name)

                val orgName: TextView = popupView.findViewById(R.id.dog_shelter_name)
                if(dog.organization != null)
                    orgName.text = dog.organization
                else
                    orgName.text = "Unknown shelter"

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
                    val addresses = Array<String>(1) {"claudiaareneee@gmail.com"}
                    EmailService.composeEmail(context, addresses, "I would like to meet ".plus(dog.name))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

}