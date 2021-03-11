package com.example.quickventory.model

data class Items(
    val user_ID: Int,
    val item_ID: String,
    val item_name: String,
    val qty: Int,
    val price: Double,
    val dept: String
)