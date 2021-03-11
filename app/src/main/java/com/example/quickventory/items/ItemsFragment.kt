package com.example.quickventory.items

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quickventory.R
import com.example.quickventory.adapters.ListItemsAdapter
import com.example.quickventory.api.Endpoints
import com.example.quickventory.api.ServiceBuilder
import com.example.quickventory.model.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsFragment : Fragment() {

    private lateinit var listItemsAdapter : ListItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPref = activity!!.getSharedPreferences("token", Context.MODE_PRIVATE)
        val theID = sharedPref.getInt("TOKEN", -1)

        Log.v("Corey", "The token" + theID.toString())

        val view = inflater.inflate(R.layout.fragment_items, container, false)
        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getItems(theID)

        call.enqueue(object : Callback<List<Items>> {
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>){
                if(response.isSuccessful){

                    val progressBarItems: ProgressBar = view.findViewById(R.id.progress)
                    progressBarItems.setVisibility(View.GONE)

                    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    listItemsAdapter = ListItemsAdapter()
                    recyclerView.adapter = listItemsAdapter
                    listItemsAdapter.setUpdateData(response.body()!!)
                    Log.v("Corey", response.body()!!.toString());
                }
            }
            override fun onFailure(call: Call<List<Items>>, t: Throwable){
                Log.v("Corey", t.toString());
            }
        })

        return view
    }
}