package com.example.a005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ActProfil : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    private lateinit var auth : FirebaseAuth
     var tema : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_profil)

        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            auth.signOut()
        }

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView

        val db = Firebase.firestore


        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent: Intent = Intent(this, ActStart::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.newPost -> {

                    val intent: Intent = Intent(this, ActNewPost::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.settings -> {

                    val intent: Intent = Intent(this, ActProfil::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemReselectedListener

                }
            }
        }

        var dateTimeDisplay = findViewById<TextView>(R.id.text_date_display)

        val sdf = SimpleDateFormat("dd/M/yyyy ")
        val currentDate = sdf.format(Date())
        dateTimeDisplay.setText(currentDate)

        var slikaProfil = findViewById<ImageView>(R.id.slikaProfil)




        }


    }
