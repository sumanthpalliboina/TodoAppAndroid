/*
package com.sumanthacademy.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    var countries:ArrayList<Country>,
    var context:Context
): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var cardView = itemView.findViewById<CardView>(R.id.cardView)
        var imageView = itemView.findViewById<ImageView>(R.id.profile)
        var titleView = itemView.findViewById<TextView>(R.id.title)
        var descriptionView = itemView.findViewById<TextView>(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val cardView:View = LayoutInflater.from(parent.context).inflate(R.layout.card_design,parent,false)
        return CountryViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        var country:Country? = countries.get(position)
        holder.imageView.setImageResource(country?.image ?: R.drawable.quotation_img)
        holder.titleView.text = country?.title.toString() ?: "Default"
        holder.descriptionView.text = context.getString(country?.description ?: R.string.app_name)

        holder.cardView.setOnClickListener {
            println("triggered")
            Toast.makeText(context,"you selected the ${country?.title.toString() ?: "Default"}",Toast.LENGTH_LONG).show()
        }

        //holder.descriptionView.text = context.getString(detailsList.get(position))
    }

    override fun getItemCount(): Int {
        return countries?.size ?: 0
    }
}
*/
