package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFlYmY4NjMzMGNkMzEyNTdhZGQ2YWEwNTBmNGEyNDM3Nzc3ZGVlZDlmNTcwYzhlZTg4OTUwNjEwOWQzZTAwZGU0MTkyODdiOTlmYmJjY2NiIn0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6ImFlYmY4NjMzMGNkMzEyNTdhZGQ2YWEwNTBmNGEyNDM3Nzc3ZGVlZDlmNTcwYzhlZTg4OTUwNjEwOWQzZTAwZGU0MTkyODdiOTlmYmJjY2NiIiwiaWF0IjoxNTcxMzI5MDc3LCJuYmYiOjE1NzEzMjkwNzcsImV4cCI6MTU3MTMzMjY3Nywic3ViIjoiIiwic2NvcGVzIjpbXX0.zmWHSgC2JnnXnNWDZ_E7lqhwh9XZIM-7TlCYVMBPA-TSGK7F93ahOTUNPEYEFUD_pxRSCtNJU5ZsE6eUQWUMGHS33Vhca7RcCDLHOTeuOGofHB3hcNvQ1N1WdLz3WKeqwc2kkdAOPE923W7b3u45tewgs2bE68M57iph_cUYAUEiZKjpbrg6N8l4q0V9Y7DIPc7d80GMwMXoJeZ8l43zhpUwpN20D6Hzk4NoSWBezIiUydjZc1qRhwLHUGDJP3OO58YDuTgG10VZh-G5Dz8jv-2xrRmfgaILa544W6PZ8Gg1_qMXKBfW5b09rniHVvMuwKlbBShuedLaBuF-5-vA3A"
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