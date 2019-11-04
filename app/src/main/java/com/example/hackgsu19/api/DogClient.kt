package com.example.hackgsu19.api

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.os.Bundle
import android.os.Handler
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import kotlin.coroutines.coroutineContext

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjcwZjM1ODc5OGQxYTk2MjNiNjIwZGFhNDJlZDk3NTAxZThiZTY3NWJkOWZjZWNkYjgyMGM1OTEwMGFkOTI0OTE1OWY3MGRiNDFkNDcxNDc1In0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6IjcwZjM1ODc5OGQxYTk2MjNiNjIwZGFhNDJlZDk3NTAxZThiZTY3NWJkOWZjZWNkYjgyMGM1OTEwMGFkOTI0OTE1OWY3MGRiNDFkNDcxNDc1IiwiaWF0IjoxNTcyMTM3NzU5LCJuYmYiOjE1NzIxMzc3NTksImV4cCI6MTU3MjE0MTM1OSwic3ViIjoiIiwic2NvcGVzIjpbXX0.w42iYFC_rB2P8T5OJCL1UMhdMtDlqdz7PUIrcABh5Y_ebBq015Rsu856VKDhqd9hak7I3tGjni-PNSkIoHR_hYV_5MiL1bJpGw3LcoXS39fLeF97gI08cc-O_2MPxZuiNf88WkTmsNcnq8g_TTvNI2FixpsDBufHz41B088ipAxzTQ8JV2K1mFAG_MpfaRLUnOi5U3HLq1TsKdBQ495-xgtqXyDjPqRqymHI8B8bqXzsyS8CwGnpnXxsnMa9vB2p2FXMiDHsagrzk4NWQQ-gRwiibhIZhyClF3j-gEPMk3MaL_Cs5ZAR7GzLEWIpfLph_ZI7I3_wXfd1QRVy6NKMqw"
    private var client: AsyncHttpClient? = null
    private var token: String? = null
    private val API_BASE_URL: String = "https://api.petfinder.com/v2"
    private val API_ANIMALS_URL: String = "/animals"
    private val API_TYPES_URL: String = "/types"
    private val API_ORGANIZATION_URL: String = "/organizations"

    init {
        client = AsyncHttpClient()
    }

    fun setToken(tok: String) {
        this.token = tok
    }

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