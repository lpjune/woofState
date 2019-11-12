package com.example.hackgsu19

import android.net.Uri
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserModel(
    var firstname: String? = "",
    var lastname: String? =  "",
    var profilePictureURL: String = "",
    var likes: MutableMap<String, Boolean> = HashMap()
)