package com.example.hackgsu19.view.adapter

import android.content.Context
import android.graphics.Color
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
        var cardImage: ImageView
        var cardName: TextView

        init {
            cardImage = itemView.findViewById(R.id.card_image)
            cardName = itemView.findViewById(R.id.card_name)
        }

        fun setDetails(dog: DogModel){
            print(dog.name)
            cardName.text = dog.name

            Picasso.with(context)
                .load(dog.imageUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.dogplaceholder)
                .into(cardImage)

            cardImage.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryLight))
        }
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var profileImage: ImageView
        val badge1: ImageView
        val badge2: ImageView
        val badge3: ImageView
        val badge4: ImageView
        val username: TextView

        init{
            profileImage = itemView.findViewById(R.id.profile_image)
            badge1 = itemView.findViewById(R.id.badge1)
            badge2 = itemView.findViewById(R.id.badge2)
            badge3 = itemView.findViewById(R.id.badge3)
            badge4 = itemView.findViewById(R.id.badge4)

            username = itemView.findViewById(R.id.name)

            badge1.setOnClickListener {}
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
        if (i == HEADERTYPE){
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.profile_header, viewGroup, false)
            return ProfileViewHolder(v)
        } else {
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
            return CardViewHolder(v)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        if (getItemViewType(i) == HEADERTYPE) {
            (viewHolder as ProfileViewHolder).setDetails()
        } else {
            print("onBindViewHolder: ".plus(i))
            val dog: DogModel = mDogList[i]
            (viewHolder as CardViewHolder).setDetails(dog)
        }
//
//                viewHolder.cardImage.setOnClickListener{
//                    context.let {
//
//                        val popupView = LayoutInflater.from(context).inflate(R.layout.dog_popup, null)
//                        val popupWindow =
//                            PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
//                        popupWindow.isFocusable = true
//
//                        val imageView: ImageView = popupView.findViewById(R.id.dog_image_expanded)
//                        imageView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorSecondaryLight))
//                        imageView.setImageDrawable(viewHolder.cardImage.drawable)
//
//                        if (imageView.drawable == null){
//                            viewHolder.cardImage.setBackgroundColor(Color.CYAN)
//                            viewHolder.cardImage.setImageDrawable(dog.image)
//                        }
//
//                        val likeButton: ImageView = popupView.findViewById(R.id.likeButton)
//
//                        val cardName: TextView = popupView.findViewById(R.id.dog_name_expanded)
//                        cardName.setText(dog.name)
//
//                        val orgName: TextView = popupView.findViewById(R.id.dog_shelter_name)
//
//                        val breeds: TextView = popupView.findViewById(R.id.dog_breeds)
//                        breeds.setText(dog.breeds)
//
//                        val quickInfo: TextView = popupView.findViewById(R.id.dog_quickinfo)
//                        quickInfo.setText(dog.gender?.plus(" ⋅ ").plus(dog.age).plus(" ⋅ ").plus(dog.size))
//
//                        // Set an elevation for the popup window
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            popupWindow.elevation = 10.0F
//                        }
//
//                        popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0)
//
//
//                        val fab = popupView.findViewById<FloatingActionButton>(R.id.addFab)
//                        fab.setOnClickListener {
//                            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//                            val myRef: DatabaseReference = database.getReference("users")
//
//                            myRef.setValue("Testing")
//                        }
//                    }
//
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return HEADERTYPE
        else
            return CARDTYPE
    }

    override fun getItemCount(): Int {
        return mDogList.size
    }

    companion object {
        private val HEADERTYPE: Int = 1
        private val CARDTYPE: Int = 2
    }
}