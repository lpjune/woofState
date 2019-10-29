package com.example.hackgsu19.view.ui.main

import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import 	androidx.appcompat.widget.Toolbar
import com.example.hackgsu19.R
import com.example.hackgsu19.api.DogClient
import com.example.hackgsu19.api.OrgClient
import com.example.hackgsu19.view.ui.login.LoginActivity
import com.facebook.login.LoginManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.tab_layout.*


class MainActivity : AppCompatActivity() {

//    val mReport1: Report = Report(
//        "Bingo",
//        "Male",
//        R.drawable.bella,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//    val mReport2: Report = Report(
//        "Tyrion",
//        "Male",
//        R.drawable.bernard,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//
//    val mReport3: Report = Report(
//        "Ally",
//        "Female",
//        R.drawable.ally,
//        "2/24/19",
//        "CARA"
//    )
//
//
//    val mReport4: Report = Report(
//        "Banana Bread",
//        "Female",
//        R.drawable.bannanabread,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//
//    val mReport5: Report = Report(
//        "Barbecue",
//        "Male",
//        R.drawable.rocky,
//        "2/24/19",
//        "PAWS Atlanta"
//    )
//
//
//    val mReport6: Report = Report(
//        "Bella",
//        "Female",
//        R.drawable.bella,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//
//    val mReport7: Report = Report(
//        "Bella",
//        "Female",
//        R.drawable.bella2,
//        "2/24/19",
//        "Cara"
//    )
//
//    val mReport8: Report = Report(
//        "Bernard",
//        "Male",
//        R.drawable.bernard,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//    val mReport9: Report = Report(
//        "Lucky",
//        "Male",
//        R.drawable.lucky,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//
//    val mReport10: Report = Report(
//        "Milo",
//        "Female",
//        R.drawable.milo,
//        "2/24/19",
//        "CARA"
//    )
//
//
//    val mReport11: Report = Report(
//        "Onyx",
//        "Female",
//        R.drawable.onyx,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//
//    val mReport12: Report = Report(
//        "Oreo",
//        "Male",
//        R.drawable.oreo,
//        "2/24/19",
//        "PAWS Atlanta"
//    )
//
//
//    val mReport13: Report = Report(
//        "Pinto Bean",
//        "Female",
//        R.drawable.pintobean,
//        "2/24/19",
//        "Atlanta Humane Society"
//    )
//
//
//    val mReport14: Report = Report(
//        "Rocky",
//        "Female",
//        R.drawable.rocky,
//        "2/24/19",
//        "Cara"
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_layout)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        configureTabLayout()
//        val adapter = TabPagerAdapter(
//            supportFragmentManager, 3
//        )

//        pager.adapter = adapter
        val dogClient: DogClient
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings_menu_feed -> {
                pager.currentItem = 0
                true
            }
            R.id.settings_menu_profile -> {
                pager.currentItem = 2
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

    fun switchFragment(fragIndex: Int){
        if (fragIndex < 4)
            pager.currentItem = fragIndex
        else
            pager.currentItem = 0

    }

    private fun configureTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText("Feed"))
        tab_layout.addTab(tab_layout.newTab().setText("Map"))
        tab_layout.addTab(tab_layout.newTab().setText("Profile"))

        val adapter = TabPagerAdapter(
            supportFragmentManager,
            tab_layout.tabCount
        )
        pager.adapter = adapter

        pager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_layout)
        )
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
    }

    fun requestPfToken(dogClient: DogClient, orgClient: OrgClient) {
        val am: AccountManager = AccountManager.get(this)
        val options = Bundle()

        am.getAuthToken(
            myAccount_,                     // Account retrieved using getAccountsByType()
            API_BASE_URL,                   // Auth scope
            options,                        // Authenticator-specific options
            this,                    // Your activity
            OnTokenAcquired(),              // Callback called when a token is successfully acquired
            Handler(OnError())              // Callback called if an error occurs
        )

        class OnTokenAcquired : AccountManagerCallback<Bundle> {

            override fun run(result: AccountManagerFuture<Bundle>) {
                // Get the result of the operation from the AccountManagerFuture.
                val bundle: Bundle = result.getResult()

                // The token is a named value in the bundle. The name of the value
                // is stored in the constant AccountManager.KEY_AUTHTOKEN.
                val token: String = bundle.getString(AccountManager.KEY_AUTHTOKEN)
                dogClient.setToken(token)
                orgClient.setToken(token)
            }
        }
    }

}
