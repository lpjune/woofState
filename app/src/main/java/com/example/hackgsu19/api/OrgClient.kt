package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class OrgClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImMwYWIxZGUwM2UyMzdlOTBhMmNiYTI5ZGM1NmU4MGI3ZjEwMjQ3MzM2YTA0NjA0N2U5NDcwZTliNGU1YzEwYWI0OWVmMDc2MmJjMzMxZTZjIn0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6ImMwYWIxZGUwM2UyMzdlOTBhMmNiYTI5ZGM1NmU4MGI3ZjEwMjQ3MzM2YTA0NjA0N2U5NDcwZTliNGU1YzEwYWI0OWVmMDc2MmJjMzMxZTZjIiwiaWF0IjoxNTcxMDcyNTgyLCJuYmYiOjE1NzEwNzI1ODIsImV4cCI6MTU3MTA3NjE4Miwic3ViIjoiIiwic2NvcGVzIjpbXX0.MqbONKEEf-ypYj8frXNu5JIcIZyuifVpd_QHYBy2wfULY4q8khiH5zxfIfKcPTaCUB00hXE0wJMwCrVh_xfQZql4e0K0VhvM4i-BcZhAa0G4y01pNg431yBg2hkh7bR2gFgclaLaWkxBD-GQr75cST4UmGQJmXA5N3K_3_ETVq60z6iNwbEOEY106lJUjPL_qYoLH3XkOPyqQw09O2VZfRcnrbLYErGFJu1-ZHE0WhRvXhbcE_bvSUIGlrMDRnwv1sgoZU_8hbIOF4b9jcAEOcHwkCQ0SO9vWKZbrYSB_bSoz9WWhR90k8rSqpovRogJiWgvCl7GOXju4dHiekfobw"
    private var client: AsyncHttpClient? = null
    private var token: String? = null
    private val API_BASE_URL: String = "https://api.petfinder.com/v2"
    private val API_ORGANIZATION_URL: String = "/organizations"

    init {
        client = AsyncHttpClient()
    }

    fun setToken(tok: String) {
        this.token = tok
    }

    fun getOrgs(handler: JsonHttpResponseHandler){
        val url = getApiUrl(API_ORGANIZATION_URL)
        print(url)
//        val url = "https://api.petfinder.com/v2/animals"
        val params = RequestParams("name", "address1")
        client?.addHeader("Authorization", "Bearer ".plus(accessToken))
        client?.get(url,params,handler)
    }

    fun getApiUrl(relativeUrl: String): String{
        return API_BASE_URL.plus(relativeUrl)
    }

    fun getOrganizationsUrl(): String {
        return API_ORGANIZATION_URL
    }

    fun getSingleOrganizationUrl(id: String): String {
        return API_ORGANIZATION_URL + id
    }

}