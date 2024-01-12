package com.example.a005

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyCustomAdapter(context: Context): BaseAdapter() {

    private val mContext: Context

    init {
        this.mContext = context
    }


    override fun getCount(): Int {
        return 5
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItem(position: Int): Any {
        return "TEST STRING"
    }


    override fun getView(p0: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val textView = TextView(mContext)
        textView.text =
            return textView
    }
}