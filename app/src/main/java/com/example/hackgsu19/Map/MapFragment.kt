package com.example.hackgsu19.Map

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_CONTACTS
import android.content.pm.PackageManager
import android.location.Location
import com.example.hackgsu19.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MapFragment:  Fragment(), OnMapReadyCallback{
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

//    var fab = view?.findViewById<FloatingActionButton>(R.id.fab)
    val ATLANTA = LatLng(33.7490,-84.3880)
    val STARKVILLE = LatLng(33.46, -88.80)
    val ZOOM_LEVEL = 15f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(com.example.hackgsu19.R.layout.fragment_map, container, false)

        val fm = childFragmentManager
        val fragment: SupportMapFragment = (fm.findFragmentById(com.example.hackgsu19.R.id.map) as SupportMapFragment)

        fragment?.getMapAsync(this)
        return rootView
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        with(mMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(STARKVILLE, ZOOM_LEVEL))
//            addMarker(MarkerOptions().position(ATLANTA))
        }
        showLocation()
    }

    private fun doShowLocation() {
        if (activity!!.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
    }

    private fun showLocation() {
        if (activity!!.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101)
        } else {
            doShowLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray) {
        if (requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            doShowLocation()
        }
    }

}