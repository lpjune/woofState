package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjZmNjJiZGM2NTE2YTQzZDkxYmU3ODcwNzU4NDU5Yjc5N2M1NGI4MTcwZTg1OThkZjFjNWU1Nzc3NjMzMTRmZDhkNWM0NGExOTI3YmVkZTI4In0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6IjZmNjJiZGM2NTE2YTQzZDkxYmU3ODcwNzU4NDU5Yjc5N2M1NGI4MTcwZTg1OThkZjFjNWU1Nzc3NjMzMTRmZDhkNWM0NGExOTI3YmVkZTI4IiwiaWF0IjoxNTcxMDc3NjY2LCJuYmYiOjE1NzEwNzc2NjYsImV4cCI6MTU3MTA4MTI2Niwic3ViIjoiIiwic2NvcGVzIjpbXX0.OfsNoxgWNnBmepSEOT2JA0juLvwpavmlUyypiW0phaWJvJmyqMPFqsVoxuDK2R1SW3dJOydxIXBok8s4q-uYCklSgMFYKTXy1PS5k1bi0zVSkwg5qc8uPjhQE40izYvAJ4hstiyyM4kw43ajkOGJgvj07SmYYPfoTBRfDlxw4kH5h3K22r4z6ZTJ-pS-l3YHvPbDPccrWqPXCxw1Kieb7ATIZLEidUif0eSz1bY9SH7aDuqqiBsQyoyMSrIH7EGr7f9LrgAIkfc6FD6xZT9JlWbWLNyPsW7y8RFfylrxubj-Wz_Zjg2qkiQODKeKFYmrfsovEMF0SUdqe-MKiSdbFw"
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