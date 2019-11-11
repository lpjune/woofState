package com.example.hackgsu19.api

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.os.Bundle
import android.os.Handler
import com.example.hackgsu19.api.Token.Companion.accessToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import kotlin.coroutines.coroutineContext

class DogClient {
    private var client: AsyncHttpClient? = null
    private val API_BASE_URL: String = "https://api.petfinder.com/v2"
    private val API_ANIMALS_URL: String = "/animals"
    private val API_TYPES_URL: String = "/types"
    private val API_ORGANIZATION_URL: String = "/organizations"
    var accessToken: String = Token.accessToken



    init {
        client = AsyncHttpClient()
    }

//    fun setToken(tok: String) {
//        DogClient.accessToken = tok
//    }
//
//    fun getToken(): String {
//        return DogClient.accessToken
//    }

    fun getAnimals(handler: JsonHttpResponseHandler){
        val url = getApiUrl(API_ANIMALS_URL)
        print(url)
//        val url = "https://api.petfinder.com/v2/animals"
        val params = RequestParams("type", "dog")
        client?.addHeader("Authorization", "Bearer ".plus(accessToken))
        client?.get(url,params,handler)
    }

    fun getApiUrl(relativeUrl: String): String{
        return API_BASE_URL.plus(relativeUrl)
    }

    fun getAnimalsUrl(): String{
        return API_ANIMALS_URL
    }

    fun getAnimalUrl(id: String): String{
        return API_ANIMALS_URL + id
    }
}