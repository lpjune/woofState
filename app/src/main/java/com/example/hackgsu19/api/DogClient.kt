package com.example.hackgsu19.api

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams

class DogClient {
    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImUzZjVkNGM1MmY4MzI4YzQxOWI0ZDlkMzIwZTYwODI3YzAzMGMxYTkyNGVkZTQ0ZGE4NWEwY2NjM2MwY2Y5ZTllZDBiMmNlZTk5NGI5ZjI2In0.eyJhdWQiOiJqaGh6bEZSa0dmT041VEtpcDR0RFNWVDhsaURTUW94VksxZ05IVUhnazBoOHR1SDA5RiIsImp0aSI6ImUzZjVkNGM1MmY4MzI4YzQxOWI0ZDlkMzIwZTYwODI3YzAzMGMxYTkyNGVkZTQ0ZGE4NWEwY2NjM2MwY2Y5ZTllZDBiMmNlZTk5NGI5ZjI2IiwiaWF0IjoxNTcyMzc4MzU0LCJuYmYiOjE1NzIzNzgzNTQsImV4cCI6MTU3MjM4MTk1NCwic3ViIjoiIiwic2NvcGVzIjpbXX0.AtYTrMVjMjbWp0plwhXAjeevZdt2AajofKjpI97xG3D9uLjjkuMZ524ShANiArAIr0l8tCZfcNaQ7eFMnX21DRB7eXJ6E8CxfOw4L517YlwbklmQkSk5tVZU4wm6bXpcBa6sz-jijiG4nL1CQ8tZuRCjbNiDsFbHlFCiGJgDfdOZzuExQQX5JU_-iaBFt_ImscWlGztAxY74gIqSgg4yVh-c0TxRuOdr4O8gNWtKz1tuU--Bvndobxe7njLEnPMDiD4bL2xvTsTN4ojMTpeHkKdfEbrGrFL6LYANvyLg2BBcogrTlzNp05CgK1dxPRkIJk95mBpmwmuj9C7tYKczzg"
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

//    fun getToken(callback: ()-> Void){
//        val database = FirebaseDatabase.getInstance().reference
//        database.child("petfinderToken").addListenerForSingleValueEvent(object:
//            ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val token: PetfinderToken? = dataSnapshot.getValue(PetfinderToken::class.java)
//                if (token != null){
////                    accessToken = token.tokenString
//                    print("*\n*\n*\n*\n")
//                    print(accessToken)
//                    callback()
//                }
//            }
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.w("uh oh", "loadPost:onCancelled", databaseError.toException())
//            }
//        })
//    }

}