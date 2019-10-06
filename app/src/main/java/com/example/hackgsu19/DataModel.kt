package com.example.hackgsu19

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class DataModel {
    fun sendRequest(context: Context, activity: FragmentActivity){
        // Simple HTTP Request:
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "http://www.google.com"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                Toast.makeText(activity, "Response is: ${response.substring(0, 500)}", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { Toast.makeText(activity, "That didn't work", Toast.LENGTH_LONG).show()})

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

}