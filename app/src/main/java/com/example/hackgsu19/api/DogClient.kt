package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjZmZmEzZjVkZjFlYzk2M2UxZGRmYTk3YzExOWViNGMzN2NhNGFlODQ5NDM3YWE4ZjRlNDdlMzFkYTI4ZDU4NjBjYzFiMmY4NWUxZjRhZmQ5In0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6IjZmZmEzZjVkZjFlYzk2M2UxZGRmYTk3YzExOWViNGMzN2NhNGFlODQ5NDM3YWE4ZjRlNDdlMzFkYTI4ZDU4NjBjYzFiMmY4NWUxZjRhZmQ5IiwiaWF0IjoxNTcyMTIzMzk1LCJuYmYiOjE1NzIxMjMzOTUsImV4cCI6MTU3MjEyNjk5NSwic3ViIjoiIiwic2NvcGVzIjpbXX0.dwoQMx32EZG9eT1ioIE-BcwbALxu8FBzKQe0b8pSI35lKdTh302qAjO7n1w_U1KUE4j_vUoddjJ7m8GI0KJ7iwPJyXZg5AkGWNrOBP-XTBBVbWlzcccM_gHWlhrO4jrHENjXp4eNNGSJTMiajPLFFNnxDwU1dGIrYt6L5p5c6AZ6id_iPLFYdhsbhKeP1h-ZdPMxhDDMUtpjK0oc5GLaSMbS2O4WAvUfG9fexQtQAgrkJihBwBb0J4kBF4_mnJfCc0BSe6I-K3uA4mhPyiK62CMGxDS-q2VLSXfzCMEUzrq0KBlmFj2O9Iks1VuEFdg-FDRRRMePbt4RYf_ztCoVzg"
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