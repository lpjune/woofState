package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImI3OGZjNmJiMmQzMjA2NGNlM2MzNTU4NDg5NTM2ZjQ5NmUyNjlhYjk4MzU2NzMyNDI2ODI2OTdjMGM3NzdmN2UzNzdlMTM5MGJmZWQ0YzBlIn0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6ImI3OGZjNmJiMmQzMjA2NGNlM2MzNTU4NDg5NTM2ZjQ5NmUyNjlhYjk4MzU2NzMyNDI2ODI2OTdjMGM3NzdmN2UzNzdlMTM5MGJmZWQ0YzBlIiwiaWF0IjoxNTcyMTMzNTI5LCJuYmYiOjE1NzIxMzM1MjksImV4cCI6MTU3MjEzNzEyOSwic3ViIjoiIiwic2NvcGVzIjpbXX0.bpF2vivRGeyn-r2l45cYu9D2QuBxuZBUtBfD8cPqFT4QWoLY7HPh3fIZDzWn3lLuJgnQmXZ9fpeH6odcubPet291HOEHdUjLPI2j4sm6D6kOQQ67qdaRe3ujPy-mAYZAa-u275_M4_xNqVBpK3-jCN8C3G1I22-09iPPuNhVRhkJV7t-w6ZOksUefdtdRdWMYg4ot2OUKbkwChgrf-u2cyOjBU5xrp_ssOlWNPYeTTTe_rObkqcETALjgd4TAtVit9fSWNwQg6A1A7tGiE0RXgtL1qynyH6SFbt3jXTrerKMU82lTe93taVkODgllSwy7ynjf87KC1uUsgv4uG0XFg"
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