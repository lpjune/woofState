package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjA5OGE1MDg2NWM1NzdmMzI5Yzk5NDBhM2Q4MGRjZDA1NWRkNmIxYzU4MzA1MDAyNjBiMmI4YjFjOTk2YzQ2ODY3MjU3NmE4MzNhZWMyMjg0In0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6IjA5OGE1MDg2NWM1NzdmMzI5Yzk5NDBhM2Q4MGRjZDA1NWRkNmIxYzU4MzA1MDAyNjBiMmI4YjFjOTk2YzQ2ODY3MjU3NmE4MzNhZWMyMjg0IiwiaWF0IjoxNTcyODk4NzYyLCJuYmYiOjE1NzI4OTg3NjIsImV4cCI6MTU3MjkwMjM2Miwic3ViIjoiIiwic2NvcGVzIjpbXX0.S_IFE7L0tRRlnWxYGdpFae17JUjveFhuT8paAJoPJNCz2hqafr0SJs1Uuv68JiUuEHoXwtio6EUwNjDSJElIuJW7BhJBLz-csn9lJCmgKeY9RUKI0Kn9o9L3xMm-P0zNcIndfdlpRpplhwm8xQ41Dp_3KCRJCLkHLheJGoFKV26wdMuhtlBO7gBoHY5OhhSBHQlzws3oM0BHyoQqXc9T01JMyxGtRApTdhSZh4WZUsqcoNJlsFxigt9tfyQrvWc9S2AvHJIDHFgDxMaBMrGTwCA9MKDHDmQEQhfrWVEZxKPVJqp0giXmUPBL1kiM0uTCL-wV0Inb6U977CriahlCrA"
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