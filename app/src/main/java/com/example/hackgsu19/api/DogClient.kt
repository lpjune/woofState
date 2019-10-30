package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjJhZGY5MDc0NDMzM2Y2MDdmYTM5MWNmYzBkZTBhOGUxOGU0OTFhYTU0NjJiMWZiZGNmZjYxZDE2ZGZhNzkxNGVlOGU2MWY1NGUxNzJiNmRmIn0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6IjJhZGY5MDc0NDMzM2Y2MDdmYTM5MWNmYzBkZTBhOGUxOGU0OTFhYTU0NjJiMWZiZGNmZjYxZDE2ZGZhNzkxNGVlOGU2MWY1NGUxNzJiNmRmIiwiaWF0IjoxNTcyNDAzMzM3LCJuYmYiOjE1NzI0MDMzMzcsImV4cCI6MTU3MjQwNjkzNywic3ViIjoiIiwic2NvcGVzIjpbXX0.LMRMLdcrICEZVEntzexLToWfk4K3KelEBn0nxeeXbJMQpCIXgMCWwdHZakCi1ncmD0ObcQhm3Uq3uz6U6DiRZQ1hQqnh9U3mYaSXZQWra8mYjqFcytMRnsL8Ed7dNqg9b0PKOoDToeBogP8DpKnf5tac3xvaO5E9wF6uWTlH9vMH6N6UAQYSQqBkxlXobEdNDsDZ2-9QKCYBiz6FomHfucMd_JHEDP1gphlryjxTh9vv8Dr6RGQCrvFWgVUFVRgMBTtl8tnNNSRhAmoDrJEcZpA1tPs-a9dgwzRLShfewutm_F1eBL8ByvTFtxpCdZsNVosteJo1A7yCYod0ZEufsg"
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