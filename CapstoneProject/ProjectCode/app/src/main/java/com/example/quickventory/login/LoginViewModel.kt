package com.example.quickventory.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickventory.api.Endpoints
import com.example.quickventory.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class LoginViewModel: ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn : LiveData<Boolean>
        get() = _isLoggedIn

    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int>
        get() = _id

    fun registerPressed() {
        Log.v("Corey", "Register Pressed")
    }

    fun forgotPasswordPressed(){
        Log.v("Corey", "Forgot Password Pressed")
    }

    fun checkCredentials(u: String, p: String) {
        if(u != null && p != null){
            val request = ServiceBuilder.buildService(Endpoints::class.java)
            val call = request.login(u!!, p!!)
            call.enqueue(object : Callback<Int>{
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.v("Corey", "API Failure")
                }
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful){

                        if(response.body()!! != -1){
                            Log.v("Corey", "The userID is " + response.body()!!.toString())
                            // Store token
                            _id.value = response.body()!!
                            _isLoggedIn.value = true
                        } else {
                            Log.v("Corey", "Invalid Credentials")
                            // Advise user to try again
                            _isLoggedIn.value = false
                        }

                    }
                }
            })
        }
    }
}