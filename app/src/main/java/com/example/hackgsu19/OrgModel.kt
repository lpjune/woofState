package com.example.hackgsu19

import org.json.JSONArray
import org.json.JSONObject

class OrgModel {
    var id: String? = null
    var name:String? = null
    var location: String? = null
    var email: String? = null

    companion object {
        fun fromJSON(jsonObject: JSONObject): OrgModel{
            val orgModel = OrgModel()
            orgModel.id = jsonObject.getString("id")
            orgModel.name = jsonObject.getString("name")
            orgModel.location = jsonObject.getString("address")
            orgModel.email = jsonObject.getString("email")

            //TODO: Remove
            print("*****\n*\n")
            print(orgModel.name + "  " + orgModel.location)
            print("*****\n*\n")

            return orgModel
        }

        fun fromJSON(jsonArray: JSONArray): ArrayList<OrgModel>{
            val orgs = ArrayList<OrgModel>()

            for (i in 0 until (jsonArray.length() - 1)) {
                orgs.add(fromJSON(jsonArray.getJSONObject(i)))
            }

            return orgs
        }
    }
}