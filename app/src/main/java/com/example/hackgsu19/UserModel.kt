package com.example.hackgsu19

import android.net.Uri
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserModel(
    var firstname: String? = "",
    var lastname: String? =  "",
    var likes: String = "",
    var profilePictureURL: String = ""
)