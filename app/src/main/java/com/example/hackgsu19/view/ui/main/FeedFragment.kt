package com.example.hackgsu19.view.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hackgsu19.R

import 	androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.api.DogClient
import com.example.hackgsu19.api.Token
import com.example.hackgsu19.view.adapter.FeedRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class FeedFragment: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    //private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private val myAdapter = FeedRecyclerAdapter()

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)

        val mRecyclerView = rootView.findViewById(R.id.manager_recycler_view) as RecyclerView // Add this
        mRecyclerView.layoutManager = GridLayoutManager(activity, 1)
        //val myAdapter = FeedRecyclerAdapter()
        myAdapter.setContext(context)
        mRecyclerView.adapter = myAdapter
        val mMapFAB = rootView.findViewById(R.id.floating_action_button_map) as FloatingActionButton
        mMapFAB.setOnClickListener {
            (activity as MainActivity).switchFragment(1)
        }
        val tokenClass = Token(this.requireContext())
        tokenClass.requestAccessToken()
        print("HERE3" + Token.accessToken)
        fetchDogs()
        return rootView
    }

    private fun fetchDogs(){
        val client = DogClient()
        print("HERE")
        client.getAnimals(object: JsonHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject?
            ) {
                val items = response?.getJSONArray("animals")
                if (items != null) {
                    val dogs = DogModel.fromJSON(items)
                    myAdapter.setDogs(dogs)
                    myAdapter.notifyDataSetChanged()
                }
                Toast.makeText(context,items?.toString(), Toast.LENGTH_LONG).show()
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