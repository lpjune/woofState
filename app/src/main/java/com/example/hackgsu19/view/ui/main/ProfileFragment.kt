package com.example.hackgsu19.view.ui.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.transition.Slide
import android.view.*
import android.view.WindowManager
import android.view.LayoutInflater
import android.widget.*
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.view.adapter.ProfileRecyclerAdapter
import com.example.hackgsu19.R
import com.facebook.AccessToken
import com.facebook.Profile
import com.facebook.ProfileTracker
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class ProfileFragment: Fragment() {
    private val adapter = ProfileRecyclerAdapter()
    private val database = FirebaseDatabase.getInstance().reference
    private val profile = Profile.getCurrentProfile()
    private val dogList = ArrayList<DogModel>()

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        val mProfileRecyclerView = rootView.findViewById(R.id.profile_recycler) as RecyclerView // Add this
        mProfileRecyclerView.layoutManager = GridLayoutManager(activity, 1)

        adapter.setContext(activity)
        mProfileRecyclerView.adapter = adapter

        val badge1 = rootView.findViewById<ImageView>(R.id.badge1)
        badge1.setOnClickListener { badgeHasBeenClicked("7 day streak Badge","Congratulations! You have a 7 day walking streak!",rootView) }

        val badge2 = rootView.findViewById<ImageView>(R.id.badge2)
        badge2.setOnClickListener { badgeHasBeenClicked("Favorite Badge","Woohoo! You have favorited a pal!",rootView) }

        val badge3 = rootView.findViewById<ImageView>(R.id.badge3)
        badge3.setOnClickListener { badgeHasBeenClicked("Picture Badge","Nice! You have posted a picture!",rootView) }

        val badge4 = rootView.findViewById<ImageView>(R.id.badge4)
        badge4.setOnClickListener { badgeHasBeenClicked("Travel Badge","Great! You have traveled with a pal!",rootView) }

        val profile: Profile = Profile.getCurrentProfile()

        val username: TextView = rootView.findViewById(R.id.name)
        username.setText(profile.name)

        val profilePicture: ImageView = rootView.findViewById(R.id.profile_image)
        Picasso.with(context).load(profile.getProfilePictureUri(300,300)).placeholder(R.drawable.default_profile_picture).into(profilePicture)

        dogList.clear()
        fetchDogs()

        return rootView
    }

    private fun fetchDogs(){
        database.child("users").child(profile.id).child("likes").addChildEventListener(object: ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val isLiked = dataSnapshot.getValue(Boolean::class.java)
                if (isLiked != null && isLiked == true)
                    getDogInfo(dataSnapshot.key!!)
                print("\n*\n*\n*\n")
                print(dataSnapshot.key)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        adapter.setDogs(dogList)
//        adapter.notifyDataSetChanged()
    }

    private fun getDogInfo(id: String){
        database.child("dogs").child(id).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dog: DogModel? = dataSnapshot.getValue(DogModel::class.java)
                print("ON DATA CHANGED *\n*\n*\n*")
                if (dog != null) {
                    dogList.add(dog)
                    adapter.notifyItemInserted(dogList.size-1)
                    print(dogList.size)
                    print(dogList)
                    print("c\nc\nc\n")
                }
            }
        })
    }

    private fun badgeHasBeenClicked(badgeTitle: String, badgeName: String, view: View){
//        Toast.makeText(activity,"Clicked ".plus(badgeName) ,Toast.LENGTH_LONG).show()

        // Initialize a new layout inflater instance
        val inflater:LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        val view = inflater.inflate(R.layout.badge_popup,null)


        val popupView = LayoutInflater.from(activity).inflate(R.layout.badge_popup, null)
        popupView.findViewById<TextView>(R.id.badge_id).setText(badgeName);
        popupView.findViewById<TextView>(R.id.badge_title_id).setText(badgeTitle);
        val popupWindow =
            PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true

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