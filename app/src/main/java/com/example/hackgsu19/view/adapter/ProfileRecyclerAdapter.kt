package com.example.hackgsu19.view.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.transition.Slide
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.R
import com.facebook.Profile
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class ProfileRecyclerAdapter: RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {
    private var mDogList = ArrayList<DogModel>()
    private val database = FirebaseDatabase.getInstance().reference
    private lateinit var context: Context
    private val profile: Profile = Profile.getCurrentProfile()

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
        Picasso.with(context)
            .load(dog.imageUrl)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.dogplaceholder)
            .into(viewHolder.cardImage)

        viewHolder.cardImage.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryLight))

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
//                database.child("users").child(profile.id).child("likes").child(dog.id.toString()).addListenerForSingleValueEvent(object:
//                    ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        if (dataSnapshot.value == true) {
//                            likeButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_white_24dp))
//                        } else {
//                            likeButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_white_24dp))
//                        }
//                    }
//                    override fun onCancelled(databaseError: DatabaseError) {
//
//                    }
//                })
//
//
//                likeButton.setOnClickListener {
//                    database.child("users").child(profile.id).child("likes").child(dog.id.toString()).addListenerForSingleValueEvent(object:
//                        ValueEventListener {
//                        override fun onDataChange(dataSnapshot: DataSnapshot) {
//                            if (dataSnapshot.value == true) {
//                                print("DOG IS UNLIKED")
//                                database.child("users").child(profile.id).child("likes").child(dog.id.toString()).setValue(false)
//                                likeButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_white_24dp))
//                            } else {
//                                print("DOG IS LIKED")
//                                database.child("users").child(profile.id).child("likes").child(dog.id.toString()).setValue(true)
//                                likeButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_white_24dp))
//                            }
//                        }
//                        override fun onCancelled(databaseError: DatabaseError) {
//
//                        }
//                    })
//                    database.child("dogs").child(dog.id.toString()).setValue(dog)
//                }

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
        return mDogList.size
    }
}