package com.example.hackgsu19.view.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*
import android.content.Intent
import android.util.Log
import com.example.hackgsu19.R
import com.example.hackgsu19.User
import com.example.hackgsu19.view.ui.main.MainActivity
import com.facebook.*
import com.google.firebase.database.*


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
        loginButton.setPermissions(Arrays.asList(EMAIL))
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
            }

            override fun onCancel() {
            }

            override fun onError(exception: FacebookException) {
            }
        })

        val profileTracker = object : ProfileTracker(){
            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                if(currentProfile != null){
                    val database = FirebaseDatabase.getInstance().reference
                    database.child("users").child(currentProfile.id).addListenerForSingleValueEvent(object: ValueEventListener{
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val user = dataSnapshot.getValue(User::class.java)

                            if (user == null) {
                                val newUser = User(currentProfile.firstName,currentProfile.lastName)
                                database.child("users").child(currentProfile.id).setValue(newUser)
                            }

                            val pictureUri: String = currentProfile.getProfilePictureUri(300,300).toString()
                            database.child("users").child(currentProfile.id).child("profilePictureURL").setValue(pictureUri)
                        }
                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.w("uh oh", "loadPost:onCancelled", databaseError.toException())
                        }
                    })

                    val myIntent = Intent(baseContext, MainActivity::class.java)
                    startActivity(myIntent)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}