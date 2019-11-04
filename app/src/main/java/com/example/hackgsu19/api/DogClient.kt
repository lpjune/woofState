package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijc4N2NhNWU1ODkxODE4NzljYTZhNDkzMzhiYWRhY2I0YzE2YzE4YjUyNGU5MjVlOTVmYWI3YmQzMDUwODhmMmVhMDZmZDYzMWI0MmVhMTFhIn0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6Ijc4N2NhNWU1ODkxODE4NzljYTZhNDkzMzhiYWRhY2I0YzE2YzE4YjUyNGU5MjVlOTVmYWI3YmQzMDUwODhmMmVhMDZmZDYzMWI0MmVhMTFhIiwiaWF0IjoxNTcyODkzNzY2LCJuYmYiOjE1NzI4OTM3NjYsImV4cCI6MTU3Mjg5NzM2Niwic3ViIjoiIiwic2NvcGVzIjpbXX0.BPoZuF1mERdAZ4tfPF4dt_MqS5uyS85ZTiPVAP6sc3wHC-Jtw-jirYO3u9uBhyZd9rr_2em8HRx0Vf7ruN-gevpEyAgv4DYAK2gxrO_wAe21HMMFC9ogUWp7TsM1ii3N9mRU6rpfs0MLubrUkf4cwkDJwohL9XL5O9ogH-wx1DNdrudhZlPIROyNjnK6it56my7q2gD1vceC60kY0F1p7FcG4MPaWinvOjdwGZTCMw_dwiAOfZVReKl1OAm7DbShhY8sXMISXdafy1cE8eWFPXc60SlelYl1SQn8ArkcwXdNC3xqkV_N_MQq9WpXDdjRB2TmKtWtTW5icljW6jA2Fg"
    private var client: AsyncHttpClient? = null

    private val API_BASE_URL: String = "https://api.petfinder.com/v2"
    private val API_ANIMALS_URL: String = "/animals"
    private val API_TYPES_URL: String = "/types"
    private val API_ORGANIZATION_URL: String = "/organizations"

    init {
        client = AsyncHttpClient()
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

    fun getOrganizationsUrl(): String {
        return API_ORGANIZATION_URL
    }

    fun getSingleOrganizationUrl(id: String): String {
        return API_ORGANIZATION_URL + id
    }
}