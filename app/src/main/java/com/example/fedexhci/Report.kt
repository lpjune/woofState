package com.example.fedexhci


import android.graphics.ImageDecoder
import android.media.Image
import java.util.*

class Report (name: String, detail: String, image: Int, date: String, org: String) {

    init {
        dogCardList.add(this)
        profileCardList.add(this)
    }

    var name:String = name
    var detail:String = detail
    var image: Int = image
    var date:String= date
    var org: String = org

    companion object {
        val dogCardList = arrayListOf<Report>()
        val profileCardList = arrayListOf<Report>()
    }
}