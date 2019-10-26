package com.example.hackgsu19

import android.graphics.drawable.Drawable
import org.json.JSONArray
import org.json.JSONObject

class DogModel {
    var id: Int? = null
    var name:String? = null
//    var organizationId: String? = null
    var url: String? = null
    var imageUrl: String? = null
    var image: Drawable? = null
    var type: String? = null
    var species: String? = null
    //TODO: Breeds, Colors,



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

            print(dogModel.name + "\n\n\n\n")

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