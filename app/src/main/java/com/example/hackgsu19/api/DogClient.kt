package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQzMDBmYTMwZmVmZjE3Yzg5OGMzMjhmZjhjNWQ2ODczYWFkNmVjMjgyODVhZWI5NTEwMjcwN2I3YzcwOWRmMmE1ZTYyZDFiOTVlYWM0YzZlIn0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6ImQzMDBmYTMwZmVmZjE3Yzg5OGMzMjhmZjhjNWQ2ODczYWFkNmVjMjgyODVhZWI5NTEwMjcwN2I3YzcwOWRmMmE1ZTYyZDFiOTVlYWM0YzZlIiwiaWF0IjoxNTcyNDA3MDExLCJuYmYiOjE1NzI0MDcwMTEsImV4cCI6MTU3MjQxMDYxMSwic3ViIjoiIiwic2NvcGVzIjpbXX0.UtgvUcyJl7b_WX5uubSmDdY-lUSuxIwUyAkqfvfm_cHN7bRhE_HnqgtHAH5EZsonn7ov5fIimSFcix1O02qfQlSvun5_0i_MP7Gqmayx99PPaKEBiGDudoTnL6p9XnPNRHwGwsXUWeTc5e4bUwTix-JAJBDUvwue5VhMyLmC5IZD0nRQYVvr11rl2d3pL4nm8n9f-8As1zmKyF5AvmEalfb5APd5ugRyRaFHEmjfNZpyJdgAeaurh15e8i5QuI13sJtZ4_SwJfDqx2zEygq2A4_imhPyf4GefR2AfsbZB95qAawpcS1rkyZC_6rH8DUWv55FnrwRDpROfh62LuKuUA\n"
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