package com.example.hackgsu19.Feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackgsu19.R

import 	androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout


class FeedFragment: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>? = null

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)

        val mRecyclerView = rootView.findViewById(R.id.manager_recycler_view) as RecyclerView // Add this
        mRecyclerView.layoutManager = GridLayoutManager(activity, 1)
        val myAdapter = FeedRecyclerAdapter()
        myAdapter.setContext(context)
        mRecyclerView.adapter = myAdapter

        val mMapFAB = rootView.findViewById(R.id.floating_action_button_map) as FloatingActionButton
        mMapFAB.setOnClickListener {
            val tabLayout = activity!!.findViewById(R.id.tab_layout) as TabLayout
            tabLayout.getTabAt(1)!!.select()
        }

        return rootView
    }
}