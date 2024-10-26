/*
package com.sumanthacademy.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapterFormBaseAdapter(
    var context:Context,
    var countries:ArrayList<Country>?
) : BaseAdapter() {
    override fun getCount(): Int {
        return countries?.size ?: 0
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {

        var itemData = countries?.get(position) ?: Country(R.drawable.sumanth_photo_qqqzsw,"Default",R.string.app_name)

        val view:View = LayoutInflater.from(parent?.context).inflate(R.layout.card_design,parent,false)

        val imageView = view.findViewById<ImageView>(R.id.profile)
        val titleView = view.findViewById<TextView>(R.id.title)
        val descriptionView = view.findViewById<TextView>(R.id.status)

        imageView.setImageResource(itemData.image)
        titleView.text = itemData.title
        descriptionView.text = context.getString(itemData.description)

        println("adapter visited")

        return view
    }
}*/
