package com.example.fedexhci.Profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fedexhci.R

class ProfileFragment: Fragment() {

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        val mProfileRecyclerView = rootView.findViewById(R.id.profile_dogs_recycler_view) as RecyclerView // Add this
        mProfileRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        mProfileRecyclerView.adapter = ProfileRecyclerAdapter()
        return rootView
    }
}