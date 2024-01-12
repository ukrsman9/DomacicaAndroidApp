package com.example.a005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.*

class ActStart : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    private  lateinit var recyclerView: RecyclerView
    private  lateinit var recyclerView2: RecyclerView
    private  lateinit var userArrayList: ArrayList<DbObjava>
    private  lateinit var userArrayList2: ArrayList<DbObjava>
    private  lateinit var myAdapter: MyAdapter
    private  lateinit var myAdapter2: MyAdapter
    private  lateinit var db: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_start)


   //botto nav ------------------------------------------------------------------------

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {

                    val intent: Intent = Intent(this, ActStart::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemReselectedListener
                    //refresh
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
    //botto nav ------------------------------------------------------------------------

        val pomoc = findViewById<Button>(R.id.pomoc)
        val pomocText= findViewById<TextView>(R.id.pomocText)
        var brojacPomoc = 0
        pomoc.setOnClickListener {


            if (brojacPomoc%2==0){

                pomocText.visibility = View.VISIBLE
            }
            else {
                pomocText.visibility = View.GONE
            }
            brojacPomoc++


        }




       val filteriDugme = findViewById(R.id.filteriDugme) as Button
        val EditText1 = findViewById(R.id.EditText1) as EditText
        val EditText2 = findViewById(R.id.EditText2) as EditText
        val EditText3 = findViewById(R.id.EditText3) as EditText
        val pretragaPoImenu = findViewById(R.id.pretragaPoImenu) as EditText
        val filteriOK = findViewById(R.id.filteriOK) as Button
        val pretragaPoImenuBtn=findViewById<Button>(R.id.pretragaPoImenuBtn)

        var brojac=2
        filteriDugme.setOnClickListener {
                if( brojac % 2 == 0)
                {
                    EditText1.visibility = View.VISIBLE
                    EditText2.visibility = View.VISIBLE
                    EditText3.visibility = View.VISIBLE
                    pretragaPoImenu.visibility = View.VISIBLE
                    filteriOK.visibility = View.VISIBLE
                    pretragaPoImenuBtn.visibility = View.VISIBLE

                }else {

                    EditText1.visibility = View.GONE
                    EditText2.visibility = View.GONE
                    EditText3.visibility = View.GONE
                    pretragaPoImenu.visibility = View.GONE
                    filteriOK.visibility = View.GONE
                    pretragaPoImenuBtn.visibility = View.GONE
                }
           brojac++
        }

        recyclerView = findViewById(R.id.notesRV)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()


        myAdapter = MyAdapter(userArrayList)

        recyclerView.adapter =myAdapter



        recyclerView2 = findViewById(R.id.notesRV2)
        recyclerView2.layoutManager= LinearLayoutManager(this)
        recyclerView2.setHasFixedSize(true)

        userArrayList2 = arrayListOf()

        myAdapter2 = MyAdapter(userArrayList2)

        recyclerView2.adapter =myAdapter2



      //  recyclerView.setBackgroundColor(Color.RED) ---------------------------------------

        pretragaPoImenuBtn.setOnClickListener {

            var varEditText4= pretragaPoImenu.text.trim()

            recyclerView.visibility = View.GONE
            recyclerView2.visibility = View.VISIBLE

            ocisti(userArrayList,userArrayList2)

            EventChangeListener3(
                varEditText4 as Editable
            )

        }


        filteriOK.setOnClickListener {

            var varEditText1 = EditText1.text.trim()
            var varEditText2 = EditText2.text.trim()
            var varEditText3 = EditText3.text.trim()



            recyclerView.visibility = View.GONE
            recyclerView2.visibility = View.VISIBLE


            ocisti(userArrayList,userArrayList2)

            EventChangeListener2(
                varEditText1 as Editable,
                varEditText2 as Editable,
                varEditText3 as Editable
            )
        }

        EventChangeListener()

    }
    private fun ocisti(userArrayList: ArrayList<DbObjava>, userArrayList2: ArrayList<DbObjava>)
    {
        userArrayList.clear()
        userArrayList2.clear()
    }


    private fun EventChangeListener(
    ) {
        db= FirebaseFirestore.getInstance()
        db.collection("objava").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {

                if(error != null)
                {
                    Log.e("firestre err", error.message.toString())
                    return
                }

                for(dc : DocumentChange in value?.documentChanges!!)
                {
                    if(dc.type == DocumentChange.Type.ADDED){
                            userArrayList.add(dc.document.toObject(DbObjava::class.java))


                    }

                }

                myAdapter.notifyDataSetChanged()

            }


        })
    }

    private fun EventChangeListener2(
        varEditText1: Editable,
        varEditText2: Editable,
        varEditText3: Editable
    ) {
        db= FirebaseFirestore.getInstance()
        db.collection("objava").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {

                if(error != null)
                {
                    Log.e("firestre err", error.message.toString())
                    return
                }

                for(dc : DocumentChange in value?.documentChanges!!)
                {
                    if(dc.type == DocumentChange.Type.ADDED){

                        if(dc.document.data.getValue("sastojak1")=="$varEditText1")
                        {
                            if (dc.document.data.getValue("sastojak2")=="$varEditText2")
                            {
                                if(dc.document.data.getValue("sastojak3")=="$varEditText3")
                                {
                                    userArrayList2.add(dc.document.toObject(DbObjava::class.java))

                                }
                            }
                            else if(dc.document.data.getValue("sastojak3")=="$varEditText2")
                            {
                                if(dc.document.data.getValue("sastojak2")=="$varEditText3")
                                {
                                    userArrayList2.add(dc.document.toObject(DbObjava::class.java))

                                }
                            }
                        }
                        else if (dc.document.data.getValue("sastojak2")=="$varEditText1")
                        {
                            if (dc.document.data.getValue("sastojak1")=="$varEditText2")
                            {
                                if(dc.document.data.getValue("sastojak3")=="$varEditText3")
                                {
                                    userArrayList2.add(dc.document.toObject(DbObjava::class.java))

                                }
                            }
                            else if(dc.document.data.getValue("sastojak3")=="$varEditText2")
                            {
                                if(dc.document.data.getValue("sastojak1")=="$varEditText3")
                                {
                                    userArrayList2.add(dc.document.toObject(DbObjava::class.java))

                                }
                            }
                        }
                        else if(dc.document.data.getValue("sastojak3")=="$varEditText1")
                        {
                            if (dc.document.data.getValue("sastojak1")=="$varEditText2")
                            {
                                if(dc.document.data.getValue("sastojak2")=="$varEditText3")
                                {
                                    userArrayList2.add(dc.document.toObject(DbObjava::class.java))

                                }
                            }
                            else if(dc.document.data.getValue("sastojak2")=="$varEditText2")
                            {
                                if(dc.document.data.getValue("sastojak1")=="$varEditText3")
                                {
                                    userArrayList2.add(dc.document.toObject(DbObjava::class.java))

                                }
                            }
                        }

                        // do ovoga se menja boja to su sva 3 sastojka----------------------------------------------------------
                        if (
                            dc.document.data.getValue("sastojak1")=="$varEditText1" ||
                            dc.document.data.getValue("sastojak2")=="$varEditText1" ||
                            dc.document.data.getValue("sastojak3")=="$varEditText1" ||
                            dc.document.data.getValue("sastojak1")=="$varEditText2" ||
                            dc.document.data.getValue("sastojak2")=="$varEditText2" ||
                            dc.document.data.getValue("sastojak3")=="$varEditText2" ||
                            dc.document.data.getValue("sastojak1")=="$varEditText3" ||
                            dc.document.data.getValue("sastojak2")=="$varEditText3" ||
                            dc.document.data.getValue("sastojak3")=="$varEditText3" )
                        {
                            userArrayList2.add(dc.document.toObject(DbObjava::class.java))

                        }

                    }

                }

                myAdapter2.notifyDataSetChanged()

            }

        })
    }

    fun EventChangeListener3(varEditText4: CharSequence) {

        db= FirebaseFirestore.getInstance()
        db.collection("objava").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {

                if(error != null)
                {
                    Log.e("firestre err", error.message.toString())
                    return
                }

                for(dc : DocumentChange in value?.documentChanges!!)
                {
                    if(dc.type == DocumentChange.Type.ADDED){
                        if(dc.document.data.getValue("imeJela")=="$varEditText4")
                        {
                            userArrayList2.add(dc.document.toObject(DbObjava::class.java))
                        }



                    }

                }

                myAdapter2.notifyDataSetChanged()

            }


        })

    }


}