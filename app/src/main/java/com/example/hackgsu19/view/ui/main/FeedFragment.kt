package com.example.hackgsu19.view.ui.main

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hackgsu19.R

import 	androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hackgsu19.DogModel
import com.example.hackgsu19.OrgModel
import com.example.hackgsu19.api.DogClient
import com.example.hackgsu19.api.OrgClient
import com.example.hackgsu19.api.Token
import com.example.hackgsu19.view.adapter.FeedRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class FeedFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var layoutManager: RecyclerView.LayoutManager? = null
    //private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private val myAdapter = FeedRecyclerAdapter()
    private lateinit var swipeLayout: SwipeRefreshLayout

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val tokenClass = Token(this.requireContext())
        tokenClass.requestAccessToken(this::fetchDogs)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)

        swipeLayout = rootView.findViewById(R.id.swipe_container)
        swipeLayout.setOnRefreshListener(this)

        val mRecyclerView = rootView.findViewById(R.id.manager_recycler_view) as RecyclerView // Add this
        mRecyclerView.layoutManager = GridLayoutManager(activity, 1)
        //val myAdapter = FeedRecyclerAdapter()
        myAdapter.setContext(context)
        mRecyclerView.adapter = myAdapter
        val mMapFAB = rootView.findViewById(R.id.floating_action_button_map) as FloatingActionButton
        mMapFAB.setOnClickListener {
            (activity as MainActivity).switchFragment(1)
        }

        print("HERE3" + Token.accessToken)
//        fetchDogs()

        return rootView
    }

    override fun onRefresh() {
        Handler().postDelayed(object: Runnable{
            override fun run() {
                fetchDogs()
                swipeLayout.isRefreshing = false
            }
        }, 0)
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
                    fetchOrgs(dogs)
                    myAdapter.setDogs(dogs)
                    myAdapter.notifyDataSetChanged()
                }
//                Toast.makeText(context,items?.toString(), Toast.LENGTH_LONG).show()
                Toast.makeText(context, items?.length().toString().plus(" items loaded"), Toast.LENGTH_LONG).show()
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

    private fun fetchOrgs(dogs: ArrayList<DogModel>){
        val client = OrgClient()
        for ((index, dog) in dogs.withIndex()){
            if (dog.organizationId != null){
                client.getSingleOrg(dog.organizationId!!, object: JsonHttpResponseHandler(){
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        response: JSONObject?
                    ) {
                        val org = response?.getJSONObject("organization")
                        if (org != null) {
                            dog.organization =  OrgModel.fromJSON(org).name
                            print("metest heck: \n\n org: ")
                            print(dog.organization)
                            print(dog.organizationId)
                            myAdapter.setDogsAtI(dog, index)
                            myAdapter.notifyDataSetChanged()
                        }
                        super.onSuccess(statusCode, headers, response)
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseString: String?,
                        throwable: Throwable?
                    ) {
                        super.onFailure(statusCode, headers, responseString, throwable)
                        dog.organization = "unknown shelter"
                        myAdapter.notifyDataSetChanged()
                    }
                })
            }
        }
    }
}