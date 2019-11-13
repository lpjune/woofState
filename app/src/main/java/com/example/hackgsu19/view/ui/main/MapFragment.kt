package com.example.hackgsu19.view.ui.main

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
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import com.example.hackgsu19.OrgModel
import com.example.hackgsu19.R
import com.example.hackgsu19.api.OrgClient
import com.example.hackgsu19.api.Token
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class MapFragment:  Fragment(), OnMapReadyCallback{
    private lateinit var mMap: GoogleMap

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
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)

        val fm = childFragmentManager
        val fragment: SupportMapFragment = (fm.findFragmentById(R.id.map) as SupportMapFragment)

        fragment.getMapAsync(this)

        val mFeedFAB = rootView.findViewById(R.id.floating_action_button_feed) as FloatingActionButton
        mFeedFAB.setOnClickListener {
            (activity as MainActivity).switchFragment(0)
        }
        val tokenClass = Token(this.requireContext())
        tokenClass.requestAccessToken(this::fetchOrgs)
        fetchOrgs()
        return rootView
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        showLocation()
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STARKVILLE, ZOOM_LEVEL))
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

    private fun fetchOrgs(){
        val client = OrgClient()
        client.getOrgs(object: JsonHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject?
            ) {
                val items = response?.getJSONArray("organizations")
                if (items != null) {
                    val orgs = OrgModel.fromJSON(items)
                    Log.i("Assert", orgs.toString())
                    // TODO set to map here
                }
                Toast.makeText(context, items?.length().toString().plus(" items loaded"), Toast.LENGTH_LONG).show()
                print(items)
                super.onSuccess(statusCode, headers, response)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                Toast.makeText(context,"Fix token: " + responseString,Toast.LENGTH_LONG).show()
                super.onFailure(statusCode, headers, responseString, throwable)
            }
        })
    }
}