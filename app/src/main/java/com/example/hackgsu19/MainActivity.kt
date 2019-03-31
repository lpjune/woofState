package com.example.hackgsu19

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.tab_layout.*



class MainActivity : AppCompatActivity() {

    val mReport1: Report = Report(
        "Mac",
        "Detail One",
        R.drawable.calendar_icon,
        "2/24/19",
        " // CARA"
    )

    val mReport2: Report = Report(
        "Report 2",
        "Detail Two",
        R.drawable.calendar_icon,
        "1/07/19",
        " // Bob Saget"
    )

    val mReport3: Report = Report(
        "Report 3",
        "Detail Three",
        R.drawable.calendar_icon,
        "3/17/19",
        " // DJ Tanner"
    )

    val mReport4: Report = Report(
        "Report 4",
        "Detail Four",
        R.drawable.calendar_icon,
        "2/04/19",
        " // Piper Chapman"
    )

    val mReport5: Report = Report(
        "Report 5",
        "Detail Five",
        R.drawable.calendar_icon,
        "3/15/19",
        " // Billy Joel"
    )
    val mReport6: Report = Report(
        "Report 6",
        "Detail Six",
        R.drawable.calendar_icon,
        "3/02/19",
        " // Santa Claus"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_layout)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        configureTabLayout()
    }

    private fun configureTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText("Feed"))
        tab_layout.addTab(tab_layout.newTab().setText("Map"))
        tab_layout.addTab(tab_layout.newTab().setText("Profile"))

        val adapter = TabPagerAdapter(supportFragmentManager,
            tab_layout.tabCount)
        pager.adapter = adapter

        pager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
}}
