package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class OrgClient {
    private var client: AsyncHttpClient? = null
    private val API_BASE_URL: String = "https://api.petfinder.com/v2"
    private val API_ORGANIZATIONS_URL: String = "/organizations"
    var accessToken: String = Token.accessToken

    init {
        client = AsyncHttpClient()
    }

    fun getOrgs(handler: JsonHttpResponseHandler){
        val url = getApiUrl(API_ORGANIZATIONS_URL)
        print(url)
//        val url = "https://api.petfinder.com/v2/animals"
        val params = RequestParams()
        params.put("location", "Starkville, MS")
        client?.addHeader("Authorization", "Bearer ".plus(accessToken))
        client?.get(url,params,handler)
    }

    fun getSingleOrg(id: String, handler: JsonHttpResponseHandler) {
        val url = getApiUrl(API_ORGANIZATIONS_URL).plus("/").plus(id)
        print(url)
        val params = RequestParams()
        client?.addHeader("Authorization", "Bearer ".plus(accessToken))
        client?.get(url,params,handler)
    }

    fun getApiUrl(relativeUrl: String): String{
        return API_BASE_URL.plus(relativeUrl)
    }

    fun getOrganizationsUrl(): String {
        return API_ORGANIZATIONS_URL
    }
}