package com.example.fedexhci.Profile

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import android.view.WindowManager
import android.view.LayoutInflater
import com.example.fedexhci.R


class ProfileFragment: Fragment() {

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        val mProfileRecyclerView = rootView.findViewById(R.id.profile_recycler) as RecyclerView // Add this
        mProfileRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        mProfileRecyclerView.adapter = ProfileRecyclerAdapter()

        val badge1 = rootView.findViewById<ImageView>(R.id.badge1)
        badge1.setOnClickListener { badgeHasBeenClicked("Badge 1",rootView) }

        val badge2 = rootView.findViewById<ImageView>(R.id.badge2)
        badge2.setOnClickListener { badgeHasBeenClicked("Badge 2",rootView) }

        val badge3 = rootView.findViewById<ImageView>(R.id.badge3)
        badge3.setOnClickListener { badgeHasBeenClicked("Badge 3",rootView) }

        val badge4 = rootView.findViewById<ImageView>(R.id.badge4)
        badge4.setOnClickListener { badgeHasBeenClicked("Badge 4",rootView) }

        return rootView
    }

    private fun badgeHasBeenClicked(badgeName: String, view: View){
        Toast.makeText(activity,"Clicked ".plus(badgeName) ,Toast.LENGTH_LONG).show()

        // Initialize a new layout inflater instance
        val inflater:LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        val view = inflater.inflate(R.layout.badge_popup,null)


        val popupView = LayoutInflater.from(activity).inflate(R.layout.badge_popup, null)
        val popupWindow =
            PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)

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

        popupWindow.showAsDropDown(popupView, 0,0)


    }

}