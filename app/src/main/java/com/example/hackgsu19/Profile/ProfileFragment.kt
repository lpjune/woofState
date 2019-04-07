package com.example.hackgsu19.Profile

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.transition.Slide
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import android.view.WindowManager
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackgsu19.R


class ProfileFragment: Fragment() {

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        val mProfileRecyclerView = rootView.findViewById(R.id.profile_recycler) as RecyclerView // Add this
        mProfileRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        val adapter = ProfileRecyclerAdapter()
        adapter.setContext(activity)
        mProfileRecyclerView.adapter = adapter

        val badge1 = rootView.findViewById<ImageView>(R.id.badge1)
        badge1.setOnClickListener { badgeHasBeenClicked("7 day streak Badge","Congratulations! You have a 7 day walking streak!",rootView) }

        val badge2 = rootView.findViewById<ImageView>(R.id.badge2)
        badge2.setOnClickListener { badgeHasBeenClicked("Favorite Badge","Woohoo! You have favorited a pal!",rootView) }

        val badge3 = rootView.findViewById<ImageView>(R.id.badge3)
        badge3.setOnClickListener { badgeHasBeenClicked("Picture Badge","Nice! You have posted a picture!",rootView) }

        val badge4 = rootView.findViewById<ImageView>(R.id.badge4)
        badge4.setOnClickListener { badgeHasBeenClicked("Travel Badge","Great! You have traveled with a pal!",rootView) }

        return rootView
    }

    private fun badgeHasBeenClicked(badgeTitle: String, badgeName: String, view: View){
//        Toast.makeText(activity,"Clicked ".plus(badgeName) ,Toast.LENGTH_LONG).show()

        // Initialize a new layout inflater instance
        val inflater:LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        val view = inflater.inflate(R.layout.badge_popup,null)


        val popupView = LayoutInflater.from(activity).inflate(R.layout.badge_popup, null)
        popupView.findViewById<TextView>(R.id.badge_id).setText(badgeName);
        popupView.findViewById<TextView>(R.id.badge_title_id).setText(badgeTitle);
        val popupWindow =
            PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true

        // Set an elevation for the popup window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10.0F
        }


        // If API level 23 or higher then execute the code
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Create a new slide animation for popup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            // Slide animation for popup window exit transition
            val slideOut = Slide()
            slideOut.slideEdge = Gravity.RIGHT
            popupWindow.exitTransition = slideOut

        }

        popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0)


    }

}