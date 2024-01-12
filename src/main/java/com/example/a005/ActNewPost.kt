package com.example.a005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.HashMap

class ActNewPost : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_new_post)

//BOTOM NAV-----------------------------------------------------------------------------------
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView

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



        //-------------------------------------------------------------------------------





        val SUbmitNew = findViewById<Button>(R.id.SUbmitNew)
        val ImeJela = findViewById<EditText>(R.id.ImeJela)
        val postupak = findViewById<EditText>(R.id.postupak)
        val sastojak1 = findViewById<EditText>(R.id.sastojak1)
        val sastojak2 = findViewById<EditText>(R.id.sastojak2)
        val  sastojak3 = findViewById<EditText>(R.id.sastojak3)



        SUbmitNew.setOnClickListener {
            val ImeJelaFun = ImeJela.text.toString()
            val postupakFun = postupak.text.toString()
            val sastojak1Fun = sastojak1.text.toString()
            val sastojak2Fun = sastojak2.text.toString()
            val sastojak3Fun = sastojak3.text.toString()

            saveFireStore(ImeJelaFun, postupakFun,sastojak1Fun,sastojak2Fun,sastojak3Fun)
        }
    }
    fun saveFireStore(
        username: String,
        password: String,
        sastojak1Fun: String,
        sastojak2Fun: String,
        sastojak3Fun: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val users: MutableMap<String, Any> = HashMap()
        users["imeJela"] = username
        users["postupak"] = password
        users["sastojak1"]= sastojak1Fun
        users["sastojak2"]= sastojak2Fun
        users["sastojak3"]= sastojak3Fun


        db.collection("objavaNaCekanju")
            .add(users)
            .addOnSuccessListener {
                Toast.makeText(this, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
    }

}