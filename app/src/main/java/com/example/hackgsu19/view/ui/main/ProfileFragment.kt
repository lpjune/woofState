package com.example.hackgsu19.view.ui.main

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.*
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.view.adapter.ProfileRecyclerAdapter
import com.example.hackgsu19.R
import com.facebook.Profile
import com.google.firebase.database.*

class ProfileFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private val adapter = ProfileRecyclerAdapter()
    private val database = FirebaseDatabase.getInstance().reference
    private val profile = Profile.getCurrentProfile()
    private val dogList = ArrayList<DogModel>()
    private lateinit var swipeLayout: SwipeRefreshLayout

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

        swipeLayout = rootView.findViewById(R.id.swipe_container)
        swipeLayout.setOnRefreshListener(this)

        dogList.clear()
        fetchDogs()

        return rootView
    }

    override fun onRefresh() {
        Handler().postDelayed(object: Runnable{
            override fun run() {
                dogList.clear()
                fetchDogs()
                swipeLayout.isRefreshing = false
            }
        }, 0)
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
                    adapter.notifyDataSetChanged()
                    print(dogList.size)
                    print(dogList)
                }
            }
        })
    }
}