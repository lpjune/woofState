package com.example.hackgsu19

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.hackgsu19.Profile.ProfileFragment
import com.example.hackgsu19.Feed.FeedFragment
import com.example.hackgsu19.Map.MapFragment

class TabPagerAdapter (fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when(position) {
            0 -> return FeedFragment()
            1 -> return MapFragment()
            2 -> return ProfileFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}