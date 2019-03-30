package com.example.fedexhci

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.tab_layout.*



class MainActivity : AppCompatActivity() {

    val mReport1: Report = Report(
        "Report 1",
        "Detail One",
        R.drawable.calendar_icon,
        "2/24/19",
        " // Jack Sparrow",
        "Manager"
    )

    val mReport2: Report = Report(
        "Report 2",
        "Detail Two",
        R.drawable.calendar_icon,
        "1/07/19",
        " // Bob Saget",
        "Manager"
    )

    val mReport3: Report = Report(
        "Report 3",
        "Detail Three",
        R.drawable.calendar_icon,
        "3/17/19",
        " // DJ Tanner",
        "Senior Manager"
    )

    val mReport4: Report = Report(
        "Report 4",
        "Detail Four",
        R.drawable.calendar_icon,
        "2/04/19",
        " // Piper Chapman",
        "Senior Manager"
    )

    val mReport5: Report = Report(
        "Report 5",
        "Detail Five",
        R.drawable.calendar_icon,
        "3/15/19",
        " // Billy Joel",
        "Executive"
    )
    val mReport6: Report = Report(
        "Report 6",
        "Detail Six",
        R.drawable.calendar_icon,
        "3/02/19",
        " // Santa Claus",
        "Executive"
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
