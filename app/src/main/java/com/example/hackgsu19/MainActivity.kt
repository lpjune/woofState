package com.example.hackgsu19

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import 	com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import 	androidx.appcompat.widget.Toolbar
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.tab_layout.*



class MainActivity : AppCompatActivity() {

    val mReport1: Report = Report(
        "Bingo",
        "Male",
        R.drawable.fergus,
        "2/24/19",
        "Atlanta Humane Society"
    )

    val mReport2: Report = Report(
        "Tyrion",
        "Male",
        R.drawable.bernard,
        "2/24/19",
        "Atlanta Humane Society"
    )


    val mReport3: Report = Report(
        "Ally",
        "Female",
        R.drawable.ally,
        "2/24/19",
        "CARA"
    )


    val mReport4: Report = Report(
        "Banana Bread",
        "Female",
        R.drawable.bannanabread,
        "2/24/19",
        "Atlanta Humane Society"
    )


    val mReport5: Report = Report(
        "Barbecue",
        "Male",
        R.drawable.rocky,
        "2/24/19",
        "PAWS Atlanta"
    )


    val mReport6: Report = Report(
        "Bella",
        "Female",
        R.drawable.bella,
        "2/24/19",
        "Atlanta Humane Society"
    )


    val mReport7: Report = Report(
        "Bella",
        "Female",
        R.drawable.bella2,
        "2/24/19",
        "Cara"
    )

    val mReport8: Report = Report(
        "Bernard",
        "Male",
        R.drawable.bernard,
        "2/24/19",
        "Atlanta Humane Society"
    )

    val mReport9: Report = Report(
        "Lucky",
        "Male",
        R.drawable.lucky,
        "2/24/19",
        "Atlanta Humane Society"
    )


    val mReport10: Report = Report(
        "Milo",
        "Female",
        R.drawable.milo,
        "2/24/19",
        "CARA"
    )


    val mReport11: Report = Report(
        "Onyx",
        "Female",
        R.drawable.onyx,
        "2/24/19",
        "Atlanta Humane Society"
    )


    val mReport12: Report = Report(
        "Oreo",
        "Male",
        R.drawable.oreo,
        "2/24/19",
        "PAWS Atlanta"
    )


    val mReport13: Report = Report(
        "Pinto Bean",
        "Female",
        R.drawable.pintobean,
        "2/24/19",
        "Atlanta Humane Society"
    )


    val mReport14: Report = Report(
        "Rocky",
        "Female",
        R.drawable.rocky,
        "2/24/19",
        "Cara"
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_layout)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        configureTabLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings_menu_profile -> {
                val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
                tabLayout.getTabAt(2)!!.select()
                true
            }
            R.id.settings_menu_logout -> {
                val instance = LoginManager.getInstance()
                instance?.logOut()
                val myIntent = Intent(this, LoginActivity::class.java)
                startActivity(myIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun configureTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText("Feed"))
        tab_layout.addTab(tab_layout.newTab().setText("Map"))
        tab_layout.addTab(tab_layout.newTab().setText("Profile"))

        val adapter = TabPagerAdapter(supportFragmentManager,
            tab_layout.tabCount)
        pager.adapter = adapter
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
