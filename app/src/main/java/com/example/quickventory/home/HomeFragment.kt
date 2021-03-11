package com.example.quickventory.home

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.quickventory.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val button = view.findViewById<Button>(R.id.logoutButton)
        button.setOnClickListener {
            val sharedPref =
                    activity!!.getSharedPreferences("token", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.remove("TOKEN")
            editor.commit()

            var bottom = activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            var search = activity!!.findViewById<ImageView>(R.id.searchImage)
            bottom.menu.performIdentifierAction(R.id.menuScan,0)
            bottom.setVisibility(View.GONE)
            search.setVisibility(View.GONE)
        }

        return view
    }
}