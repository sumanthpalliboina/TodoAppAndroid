package com.sumanthacademy.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumanthacademy.myapplication.model.IntroImageItem
import com.sumanthacademy.myapplication.R

class IntroImageAdapter: ListAdapter<IntroImageItem, IntroImageAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<IntroImageItem>(){
        override fun areContentsTheSame(oldItem: IntroImageItem, newItem: IntroImageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: IntroImageItem, newItem: IntroImageItem): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var introImage = itemView.findViewById<ImageView>(R.id.introImage)
        var imageTitle = itemView.findViewById<AppCompatTextView>(R.id.title)

        fun bindData(item: IntroImageItem){
            imageTitle?.let{
                it.text = item.title.toString()
            }
            Glide.with(itemView)
                .load(item.url)
                .into(introImage)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popup_intro_item,parent,false)
        return ViewHolder(view)
    }
}