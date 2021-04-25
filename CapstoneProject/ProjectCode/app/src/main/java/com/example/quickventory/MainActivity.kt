package com.example.quickventory


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.quickventory.add.AddFragment
import com.example.quickventory.home.HomeFragment
import com.example.quickventory.items.ItemsFragment
import com.example.quickventory.login.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val itemsFragment = ItemsFragment()
        val loginFragment = LoginFragment()
        val addFragment = AddFragment()
        val developerFragment = DevelopersFragment()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        var searchImage: ImageView = findViewById(R.id.searchImage)

        val sharedPref = getSharedPreferences("token", Context.MODE_PRIVATE)
        val theID = sharedPref.getInt("TOKEN", -1)

        if(theID == -1){
            bottomNavigation.setVisibility(View.GONE)
            searchImage.setVisibility(View.GONE)
            makeCurrentFragment(loginFragment)
        } else {
            bottomNavigation.setVisibility(View.VISIBLE)
            searchImage.setVisibility(View.VISIBLE)
            makeCurrentFragment(homeFragment)
        }

        bottomNavigation.selectedItemId = R.id.menuHome

        bottomNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.menuHome -> makeCurrentFragment(homeFragment)
                R.id.menuItems -> makeCurrentFragment(itemsFragment)
                R.id.menuScan -> makeCurrentFragment(loginFragment)
                R.id.menuAdd -> makeCurrentFragment(addFragment)
                R.id.menuDeveloper -> makeCurrentFragment(developerFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.wrapper, fragment)
        commit()
    }
}