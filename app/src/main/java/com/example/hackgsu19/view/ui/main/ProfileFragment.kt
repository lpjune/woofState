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

        dogList.clear()
        fetchDogs()

        return rootView
    }

    private fun fetchDogs(){
        database.child("users").child(profile.id).child("likes").addChildEventListener(object: ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {}

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                adapter.notifyDataSetChanged()
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val isLiked = dataSnapshot.getValue(Boolean::class.java)
                if (isLiked != null && isLiked == true)
                    getDogInfo(dataSnapshot.key!!)
                print(dataSnapshot.key)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                adapter.notifyDataSetChanged()
            }
        })

        adapter.setDogs(dogList)
    }

    private fun getDogInfo(id: String){
        database.child("dogs").child(id).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dog: DogModel? = dataSnapshot.getValue(DogModel::class.java)
                if (dog != null) {
                    dogList.add(dog)
                    adapter.notifyItemInserted(dogList.size-1)
                    print(dogList.size)
                    print(dogList)
                }
            }
        })
    }
}