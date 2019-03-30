package com.example.fedexhci


import android.graphics.ImageDecoder
import android.media.Image
import java.util.*

class Report (title: String, detail: String, image: Int, date: String, author: String, department: String) {

    init {
        when(department) {
            "Manager" -> managerReportList.add(this)
            "Senior Manager" -> seniorManagerReportList.add(this)
            "Executive" -> executiveReportList.add(this)
        }
    }

    var title:String = title
    var detail:String = detail
    var image: Int = image
    var date:String= date
    var author: String = author
    var department: String = department

    companion object {
        val managerReportList = arrayListOf<Report>()
        val seniorManagerReportList = arrayListOf<Report>()
        val executiveReportList = arrayListOf<Report>()
    }
}