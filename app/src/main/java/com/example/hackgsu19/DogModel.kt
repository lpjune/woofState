package com.example.hackgsu19

import android.graphics.drawable.Drawable
import org.json.JSONArray
import org.json.JSONObject

data class DogModel(var id: Int? = null,
                    var name:String? = null,
                    //    var organizationId: String? = null
                    var url: String? = null,
                    var imageUrl: String? = null,
                    var image: Drawable? = null,
                    var breeds: String? = null,
                    var age: String? = null,
                    var gender: String? = null,
                    var size: String? = null) {

    companion object {
        fun fromJSON(jsonObject: JSONObject): DogModel{
            val dogModel = DogModel()
            dogModel.id = jsonObject.getInt("id")
            dogModel.name = jsonObject.getString("name")
//            dogModel.organizationId = jsonObject.getString("organzation_id")
            dogModel.url = jsonObject.getString("url")

            val photos: JSONArray = jsonObject.getJSONArray("photos")
            if(photos.length() > 0) {
                val photo: JSONObject = photos[0] as JSONObject
                dogModel.imageUrl = photo.getString("full")
            }

            val breeds: JSONObject = jsonObject.getJSONObject("breeds")
            if(breeds.getBoolean("unknown"))
                dogModel.breeds = "Unknown"
            else{
                dogModel.breeds = breeds.getString("primary")
                val secondary = breeds.getString("secondary")
                if (secondary != "null")
                    dogModel.breeds = dogModel.breeds.plus(" ").plus(secondary)

                val mixed = breeds.getBoolean("mixed")
                if (mixed)
                    dogModel.breeds = dogModel.breeds.plus(" Mix")
            }

            dogModel.age = jsonObject.getString("age")
            dogModel.gender = jsonObject.getString("gender")
            dogModel.size = jsonObject.getString("size")


            //TODO: Remove
            print("*****\n*\n")
            print(dogModel.name + "  " + photos.toString())
            print("*****\n*\n")

            return dogModel
        }

        fun fromJSON(jsonArray: JSONArray): ArrayList<DogModel>{
            val dogs = ArrayList<DogModel>()

            for (i in 0 until (jsonArray.length() - 1)) {
                dogs.add(fromJSON(jsonArray.getJSONObject(i)))
            }

            return dogs
        }

    }
}