package com.example.hackgsu19.api

object Api {
    private const val OAUTH_TOKEN_PATH = "https://api.petfinder.com/v2/oauth2/token"
    private const val BASE_DOG_PATH = "https://api.petfinder.com/v2/animals?type=dog"

    fun getTokenPath(): String{
        return OAUTH_TOKEN_PATH
    }

    fun getDogPath(): String{
        return BASE_DOG_PATH
    }
}