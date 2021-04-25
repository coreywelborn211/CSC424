package com.example.quickventory.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quickventory.R
import com.example.quickventory.model.Items

class ListItemsAdapter : RecyclerView.Adapter<ListItemsAdapter.MyViewHolder>() {
    lateinit var items : List<Items>

    fun setUpdateData(items: List<Items>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName = view.findViewById<TextView>(R.id.itemName)
        val itemQuantity = view.findViewById<TextView>(R.id.itemQuantity)
        val itemPrice = view.findViewById<TextView>(R.id.itemPrice)
        val itemDepartment = view.findViewById<TextView>(R.id.itemDepartment)

        fun bind(data : Items){
            itemName.text = data.item_name
            itemQuantity.text = "Qty: " + data.qty.toString()
            itemPrice.text = "$" + data.price.toString()
            itemDepartment.text = data.dept
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }
}