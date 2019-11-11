package com.example.hackgsu19.api

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Token {
    var context: Context

    companion object {
        var accessToken: String = ""
    }

        constructor(context: Context) {
            this.context = context
        }

        fun getToken(): String {
            return Token.accessToken
        }

        fun setToken(token: String) {
            Token.accessToken = token
        }

        fun requestAccessToken() {
            val TOKENURL = "https://api.petfinder.com/v2/oauth2/token"
            val CLIENTID = "jhhzlFRkGfON5TKip4tDSVT8liDSQoxVK1gNHUHgk0h8tuH09F"
            val CLIENTSECRET = "XwBXuKoTwn5YUqDqwA7MTbJJRWRa3SkPIWpqy0Os"
            val request = object : StringRequest(
                Request.Method.POST, TOKENURL,
                Response.Listener { response ->
                    Log.e("Success Response: ", response.toString())
                    val obj = JSONObject(response)
                    val token: String = obj.getString("access_token")
                    Log.e("Actual Token: ", token)
                    this.setToken(token)
                },
                Response.ErrorListener { error -> Log.e("Error Response = ", error.toString()) }) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params.put("grant_type", "client_credentials")
                    params.put("client_id", CLIENTID)
                    params.put("client_secret", CLIENTSECRET)
                    return params
                }

                @Override
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Accept", "application/json")
                    headers.put("Content-Type", "application/x-www-form-urlencoded");
                    return headers
                }
            }
            Volley.newRequestQueue(this.context).add(request)
        }

//    fun addToRequestQ() {
//    TODO("add animal/org request to volley request queue")
//    }
}