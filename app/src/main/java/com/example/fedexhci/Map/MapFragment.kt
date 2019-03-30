package com.example.fedexhci.Map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fedexhci.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import com.google.android.gms.maps.model.CameraPosition
import android.R.attr.fragment
import android.content.Intent


class MapFragment:  Fragment(), OnMapReadyCallback{
    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

//    var fab = view?.findViewById<FloatingActionButton>(R.id.fab)
    val SYDNEY = LatLng(-33.862, 151.21)
    val ATLANTA = LatLng(33.7490,-84.3880)
    val ZOOM_LEVEL = 13f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(com.example.fedexhci.R.layout.fragment_map, container, false)

        val fm = childFragmentManager
        val fragment: SupportMapFragment = (fm.findFragmentById(com.example.fedexhci.R.id.map) as SupportMapFragment)

        fragment?.getMapAsync(this)
        return rootView
    }



    override fun onMapReady(googleMap: GoogleMap) {
        googleMap ?: return
        with(googleMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(ATLANTA, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(ATLANTA))
        }
    }

}