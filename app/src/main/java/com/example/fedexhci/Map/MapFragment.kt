package com.example.fedexhci.Map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fedexhci.R

class MapFragment: Fragment() {


    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

//    var fab = view?.findViewById<FloatingActionButton>(R.id.fab)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        return rootView
    }
}