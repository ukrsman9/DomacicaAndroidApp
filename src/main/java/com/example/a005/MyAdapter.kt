package com.example.a005

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList : ArrayList<DbObjava>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun getItemCount(): Int {
       return userList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user : DbObjava = userList[position]
        holder.idTVNote.text = user.imeJela
        holder.idTVDate.text = user.postupak
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_objava,parent,false)
        return  MyViewHolder(itemView)
    }



    public class MyViewHolder(itemView : View ) : RecyclerView.ViewHolder(itemView)
    {
        val idTVNote : TextView = itemView.findViewById(R.id.idTVNote)
        val idTVDate : TextView = itemView.findViewById(R.id.idTVDate)

    }

}