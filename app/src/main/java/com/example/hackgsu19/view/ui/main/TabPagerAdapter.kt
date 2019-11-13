package com.example.hackgsu19.view.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hackgsu19.view.ui.main.ProfileFragment
import com.example.hackgsu19.view.ui.main.FeedFragment
import com.example.hackgsu19.view.ui.main.MapFragment

class TabPagerAdapter (fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        when(position) {
            0 -> return FeedFragment()
            1 -> return MapFragment()
            2 -> return ProfileFragment()
            else -> return FeedFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}