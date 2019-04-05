package com.example.hackgsu19

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*
import android.content.Intent
import com.facebook.*


class LoginActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(this.applicationContext)
        AppEventsLogger.activateApp(application)
        setContentView(R.layout.login_layout)

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn: Boolean = accessToken != null && !accessToken.isExpired
        if(isLoggedIn) {
            val myIntent = Intent(baseContext, MainActivity::class.java)
            startActivity(myIntent)
        }

        val EMAIL = "email"

        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setReadPermissions(Arrays.asList(EMAIL))
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val myIntent = Intent(baseContext, MainActivity::class.java)
                startActivity(myIntent)
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}