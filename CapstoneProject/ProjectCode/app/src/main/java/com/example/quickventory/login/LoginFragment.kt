package com.example.quickventory.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.quickventory.MainActivity
import com.example.quickventory.R
import com.example.quickventory.databinding.FragmentLoginBinding
import com.example.quickventory.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_login, container, false)
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login,
                container,
                false
        )

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.loginButton.setOnClickListener {
            login()
            binding.loginButton.setEnabled(false)
        }

        return binding.root
    }

    // Check Credentials and Observe isLoggedIn LiveData
    private fun login() {

        viewModel.checkCredentials(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
            if(it){
                if(activity != null) {
                    Log.v("Corey", "setting id" + viewModel.id.value.toString())
                    val sharedPref = activity!!.getSharedPreferences("token", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.apply{
                        putInt("TOKEN", viewModel.id.value!!.toInt())
                    }
                    editor.commit()

                    var bottom = activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    bottom.menu.findItem(R.id.menuHome).setChecked(true)
                    bottom.menu.performIdentifierAction(R.id.menuHome,0)
                    bottom.setVisibility(View.VISIBLE)

                    var search = activity!!.findViewById<ImageView>(R.id.searchImage)
                    search.setVisibility(View.VISIBLE)
                }
            } else {
                binding.tryAgain.setVisibility(View.VISIBLE)
                binding.loginButton.setEnabled(true)
            }
        })
    }
}