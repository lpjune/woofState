package com.example.hackgsu19.view.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.transition.Slide
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.EmailService
import com.example.hackgsu19.R
import com.facebook.Profile
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class ProfileRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDogList = ArrayList<DogModel>()
    private val database = FirebaseDatabase.getInstance().reference
    private lateinit var context: Context
    private val profile: Profile = Profile.getCurrentProfile()

    fun setDogs(dogs: ArrayList<DogModel>){
        mDogList = dogs
    }
    
    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cardImage: ImageView
        private var cardName: TextView

        init {
            cardImage = itemView.findViewById(R.id.card_image)
            cardName = itemView.findViewById(R.id.card_name)
        }

        fun setDetails(dog: DogModel){
            print(dog.name)
            cardName.text = dog.name

            if (dog.imageUrl == null){
                cardImage.setBackgroundColor(Color.GRAY)
            } else {
                Picasso.with(context)
                    .load(dog.imageUrl)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.dogplaceholder)
                    .into(cardImage)
                cardImage.setBackgroundColor(ContextCompat.getColor(context,R.color.colorSecondaryLight))
            }

            cardImage.setOnClickListener { onCardClicked(dog, cardImage.drawable) }
        }
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var profileImage: ImageView
        private val badge1: ImageView
        private val badge2: ImageView
        private val badge3: ImageView
        private val badge4: ImageView
        private val username: TextView

        init{
            profileImage = itemView.findViewById(R.id.profile_image)
            badge1 = itemView.findViewById(R.id.badge1)
            badge2 = itemView.findViewById(R.id.badge2)
            badge3 = itemView.findViewById(R.id.badge3)
            badge4 = itemView.findViewById(R.id.badge4)

            username = itemView.findViewById(R.id.name)
        }

        fun setDetails(){
            badge1.setOnClickListener { Toast.makeText(context, "7 day streak Badge, Congratulations! You have a 7 day walking streak!",Toast.LENGTH_LONG).show()}
            badge2.setOnClickListener { Toast.makeText(context, "Favorite Badge, Woohoo! You have favorited a pal!",Toast.LENGTH_LONG).show()}
            badge3.setOnClickListener { Toast.makeText(context, "Picture Badge, Nice! You have posted a picture!",Toast.LENGTH_LONG).show()}
            badge4.setOnClickListener { Toast.makeText(context, "Travel Badge, Great! You have traveled with a pal!",Toast.LENGTH_LONG).show()}

            val profile: Profile = Profile.getCurrentProfile()
            username.setText(profile.name)
            Picasso.with(context).load(profile.getProfilePictureUri(300,300)).placeholder(R.drawable.default_profile_picture).into(profileImage)
        }
    }

    fun setContext(context: Context?){
        context?.let { this.context = context }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        if (i == TYPE_HEADER){
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.profile_header, viewGroup, false)
            return ProfileViewHolder(v)
        } else {
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
            return CardViewHolder(v)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        if (getItemViewType(i) == TYPE_HEADER) {
            (viewHolder as ProfileViewHolder).setDetails()
        } else {
            print("onBindViewHolder: ".plus(i))
            val dog: DogModel = mDogList[i - 1]
            (viewHolder as CardViewHolder).setDetails(dog)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_HEADER
        else
            return TYPE_CARD
    }

    override fun getItemCount(): Int {
        return (mDogList.size + 1)
    }

    private fun onCardClicked (dog: DogModel, image: Drawable?){
        val popupView = LayoutInflater.from(context).inflate(R.layout.dog_popup, null)
        val popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true

        val imageView: ImageView = popupView.findViewById(R.id.dog_image_expanded)
        imageView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorSecondaryLight))
        imageView.setImageDrawable(image)

        if (imageView.drawable == null){
            imageView.setBackgroundColor(Color.CYAN)
            imageView.setImageDrawable(dog.image)
        }

        val likeButton: ImageView = popupView.findViewById(R.id.likeButton)
        likeButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_white_24dp))
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
        cardName.text = dog.name

        val orgName: TextView = popupView.findViewById(R.id.dog_shelter_name)
        if(dog.organization != null)
            orgName.text = dog.organization
        else
            orgName.text = "Unknown shelter"

        val breeds: TextView = popupView.findViewById(R.id.dog_breeds)
        breeds.text = dog.breeds

        val quickInfo: TextView = popupView.findViewById(R.id.dog_quickinfo)
        quickInfo.text = dog.gender?.plus(" ⋅ ").plus(dog.age).plus(" ⋅ ").plus(dog.size)

        popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0)


        val fab = popupView.findViewById<FloatingActionButton>(R.id.addFab)
        fab.setOnClickListener {
            val addresses = Array<String>(1) {"claudiaareneee@gmail.com"}
            EmailService.composeEmail(context, addresses, "I would like to meet ".plus(dog.name))
        }
    }

    companion object {
        private const val TYPE_HEADER: Int = 1
        private const val TYPE_CARD: Int = 2
    }
}