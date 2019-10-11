package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjMzN2EwZDBiNDEwYzVhMDZhOWY4NzIwODgyNjdlN2MxMmZlM2E2M2JkZmFmNWVjN2JhNjdiMTZkYjMxOGZhZDU0N2Q0MTUwNjk1YzA5OWRhIn0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6IjMzN2EwZDBiNDEwYzVhMDZhOWY4NzIwODgyNjdlN2MxMmZlM2E2M2JkZmFmNWVjN2JhNjdiMTZkYjMxOGZhZDU0N2Q0MTUwNjk1YzA5OWRhIiwiaWF0IjoxNTcwODI1MTYzLCJuYmYiOjE1NzA4MjUxNjMsImV4cCI6MTU3MDgyODc2Mywic3ViIjoiIiwic2NvcGVzIjpbXX0.UqOQpLg6vspO8GdEXFhweunvSCf4iugTD2zU31oF2iSbC8ILx6AINmkYnVH179mnwLEHzWVVOHRW9s7-uvqO0GdV9EL6YSFtH0DERwpS605nrfToa9Ny3d01Rp0N4PkMT410DZI7aj0Akcsn85PnWYwxnJjRSTePtFI9cKCi6XaVJtnqPYhHySaqvUsYJ_N1DdP2Z6aelb-LoTpeSqoLripxaSelh45Vi0f90T7hxcysGtPddOuj-fjRaRJXYYXA7G_0z1elNIsbjgMku3kczGtqptgSTD_bi6iZ7VqCyRHA42iJqxZjZITebMhOb2dXSbM45ehkbJMDI4xiT2jx8Q"
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