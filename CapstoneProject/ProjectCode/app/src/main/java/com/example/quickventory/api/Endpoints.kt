package com.example.quickventory.api

import com.example.quickventory.model.HomePageData
import com.example.quickventory.model.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    // Get all items for user
    @GET("list")
    fun getItems(@Query("user") user: Int): Call<List<Items>>

    @GET("login")
    fun login(@Query("username") username: String, @Query("password") password: String): Call<Int>

    @GET("home")
    fun getHomepage(@Query("user") user: Int): Call<List<HomePageData>>

    // Login (Username and Password)


}