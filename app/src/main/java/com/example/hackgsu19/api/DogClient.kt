package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjViMWExOGYzMGIzNWYzMWViMDE1NDNhNzk2MjA5ZWM5NGI4OTM1MWMwZjVkY2M0ZWQxNTYyOTZhYTA1ZTE3ZmIwZWQxOTYyODBjOTU3ZTU5In0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6IjViMWExOGYzMGIzNWYzMWViMDE1NDNhNzk2MjA5ZWM5NGI4OTM1MWMwZjVkY2M0ZWQxNTYyOTZhYTA1ZTE3ZmIwZWQxOTYyODBjOTU3ZTU5IiwiaWF0IjoxNTcyMzAxOTIyLCJuYmYiOjE1NzIzMDE5MjIsImV4cCI6MTU3MjMwNTUyMiwic3ViIjoiIiwic2NvcGVzIjpbXX0.V-rL2NsScxkAM8HTlyCXb4LlcRQA2MTfYU5ILt01HiSuGKoZqENZavS3cfRvxTF-RyntpUtNw-zgoNsX6TZxdDko4OP5STplPJHYGv-_VSTMAQPe6S0qAFJiXsfsmui1Ob-R1w-bDg6yPksCcjKx4tFPaZ6v51mXwQyTO5OpAowAmvslXywsN6tRrNIrQXjfdpMdqX4vUOF5OOyhJRGwQmepjzoNGTsrMRxJTihTD3okHi-1THrIuPyYq2x4nD-6KPVJyt5CkZvnS00vrXHrEVHDNKiJS2R8XYMlzeweCVTAzu645FDxHY6zeCMjeG37Idv7wegi8-tOoApRqyomrQ"
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