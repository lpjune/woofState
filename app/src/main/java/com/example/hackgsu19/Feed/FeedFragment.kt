package com.example.hackgsu19.Feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.hackgsu19.R

import 	androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hackgsu19.DataModel
import com.example.hackgsu19.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.tab_layout.*
import kotlinx.android.synthetic.main.tab_layout.view.*


class FeedFragment: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

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
            (activity as MainActivity).switchFragment(1)
            val dataModel = DataModel()
            dataModel.sendRequest(context!!,activity as FragmentActivity)
        }

        return rootView
    }
}