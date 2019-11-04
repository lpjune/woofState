package com.example.hackgsu19.view.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import    androidx.appcompat.widget.Toolbar
import com.example.hackgsu19.R
import com.example.hackgsu19.api.DogClient
import com.example.hackgsu19.api.OrgClient
import com.example.hackgsu19.view.ui.login.LoginActivity
import com.facebook.login.LoginManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.tab_layout.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import com.example.hackgsu19.api.Token
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_layout)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        configureTabLayout()
//        val adapter = TabPagerAdapter(
//            supportFragmentManager, 3
//        )

        //        pager.adapter = adapter
        val tokenClass = Token(this.applicationContext)
        tokenClass.requestAccessToken()
//        var token = Token.accessToken


//        val TOKENURL = "https://api.petfinder.com/v2/oauth2/token"
//        val CLIENTID = "jhhzlFRkGfON5TKip4tDSVT8liDSQoxVK1gNHUHgk0h8tuH09F"
//        val CLIENTSECRET = "XwBXuKoTwn5YUqDqwA7MTbJJRWRa3SkPIWpqy0Os"
//        val request = object : StringRequest(Request.Method.POST, TOKENURL,
//            Response.Listener { response ->
//                Log.e("Success Response: ", response.toString())
//                val obj = JSONObject(response)
//                val token: String = obj.getString("access_token")
//                Log.e("Actual Token: ", token)
//                val dogClient = DogClient().setToken(token)
//                val orgClient = OrgClient().setToken(token)
//            },
//            Response.ErrorListener { error -> Log.e("Error Response = ", error.toString()) }) {
//            override fun getParams(): Map<String, String> {
//                val params = HashMap<String, String>()
//                params.put("grant_type", "client_credentials")
//                params.put("client_id", CLIENTID)
//                params.put("client_secret", CLIENTSECRET)
//                return params
//            }
//            @Override
//            override fun getHeaders(): Map<String, String> {
//                val headers= HashMap<String,String>()
//                headers.put("Accept","application/json")
//                headers.put("Content-Type","application/x-www-form-urlencoded")
//                return headers
//            }
//        }
//        Volley.newRequestQueue(this).add(request)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings_menu_feed -> {
                pager.currentItem = 0
                true
            }
            R.id.settings_menu_profile -> {
                pager.currentItem = 2
                true
            }
            R.id.settings_menu_logout -> {
                val instance = LoginManager.getInstance()
                instance?.logOut()
                val myIntent = Intent(this, LoginActivity::class.java)
                startActivity(myIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun switchFragment(fragIndex: Int) {
        if (fragIndex < 4)
            pager.currentItem = fragIndex
        else
            pager.currentItem = 0

    }

    private fun configureTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText("Feed"))
        tab_layout.addTab(tab_layout.newTab().setText("Map"))
        tab_layout.addTab(tab_layout.newTab().setText("Profile"))

        val adapter = TabPagerAdapter(
            supportFragmentManager,
            tab_layout.tabCount
        )
        pager.adapter = adapter

        pager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_layout)
        )
        tab_layout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

//    fun requestAccessToken() {
//        val TOKENURL = "https://api.petfinder.com/v2/oauth2/token"
//        val CLIENTID = "jhhzlFRkGfON5TKip4tDSVT8liDSQoxVK1gNHUHgk0h8tuH09F"
//        val CLIENTSECRET = "XwBXuKoTwn5YUqDqwA7MTbJJRWRa3SkPIWpqy0Os"
//        val request = object : StringRequest(Request.Method.POST, TOKENURL,
//            Response.Listener { response ->
//                Log.e("Success Response: ", response.toString())
//                val obj = JSONObject(response)
//                val token: String = obj.getString("access_token")
//                Log.e("Actual Token: ", token)
//                },
//            Response.ErrorListener { error -> Log.e("Error Response = ", error.toString()) }) {
//            override fun getParams(): Map<String, String> {
//                val params = HashMap<String, String>()
//                params.put("grant_type", "client_credentials")
//                params.put("client_id", CLIENTID)
//                params.put("client_secret", CLIENTSECRET)
//                return params
//            }
//            @Override
//        override fun getHeaders(): Map<String, String> {
//                val headers= HashMap<String,String>()
//            headers.put("Accept","application/json")
//            headers.put("Content-Type","application/x-www-form-urlencoded");
//            return headers
//        }
//        }
//        Volley.newRequestQueue(this).add(request)
//    }

}


