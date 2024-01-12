package com.example.a005

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class login : Fragment() {



    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        findNavController().navigate(R.id.signUp3)
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_login, container, false)



        return view
    }





}
