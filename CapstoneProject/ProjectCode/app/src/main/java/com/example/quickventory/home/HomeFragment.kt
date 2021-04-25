package com.example.quickventory.home

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quickventory.R
import com.example.quickventory.adapters.ListItemsAdapter
import com.example.quickventory.api.Endpoints
import com.example.quickventory.api.ServiceBuilder
import com.example.quickventory.model.HomePageData
import com.example.quickventory.model.Items
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        val sharedPref = activity!!.getSharedPreferences("token", Context.MODE_PRIVATE)
        val theID = sharedPref.getInt("TOKEN", -1)

        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getHomepage(theID)

        call.enqueue(object : Callback<List<HomePageData>> {
            override fun onResponse(call: Call<List<HomePageData>>, response: Response<List<HomePageData>>){
                if(response.isSuccessful){
                    Log.v("Coreys", response.body()!!.toString());

                    val count = view.findViewById<TextView>(R.id.itemCountText)
                    count.text = response.body()!![0].count.toString()

                    val sum = view.findViewById<TextView>(R.id.totalAssetText)

                    if(response.body()!![0].sum == null){
                        sum.text = "$0.00"
                    } else {
                        sum.text = "$" + response.body()!![0].sum.toString()
                    }
                }
            }
            override fun onFailure(call: Call<List<HomePageData>>, t: Throwable){
                Log.v("Corey", t.toString());
            }
        })

        val home = view.findViewById<LinearLayout>(R.id.layoutHomeTile)
        home.setOnClickListener {
            var bottom = activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottom.menu.performIdentifierAction(R.id.menuHome,0)
        }
        val inventory = view.findViewById<LinearLayout>(R.id.layoutInventoryTile)
        inventory.setOnClickListener {
            var bottom = activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottom.menu.performIdentifierAction(R.id.menuItems,0)
        }
        val add = view.findViewById<LinearLayout>(R.id.layoutAddItem)
        add.setOnClickListener {
            var bottom = activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottom.menu.performIdentifierAction(R.id.menuAdd,0)
        }

        val developer = view.findViewById<LinearLayout>(R.id.layoutDevelopersTile)
        developer.setOnClickListener {
            var bottom = activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottom.menu.performIdentifierAction(R.id.menuDeveloper,0)
        }

        val lightsaber = view.findViewById<TextView>(R.id.lightsaber)
        lightsaber.text = Date().getMonth().toString() + "/" + Date().getDate().toString() + "/" + (1900 + Date().getYear()).toString()

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