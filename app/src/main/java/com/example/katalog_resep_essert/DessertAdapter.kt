package com.example.katalog_resep_essert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DessertAdapter(private val listDessert: List<Dessert>) :
    RecyclerView.Adapter<DessertAdapter.ListViewHolder>() {

    // ViewHolder: Menghubungkan variabel dengan view di item_dessert.xml
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgDessert)
        val tvName: TextView = itemView.findViewById(R.id.tvDessertName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dessert, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, image) = listDessert[position]
        holder.imgPhoto.setImageResource(image)
        holder.tvName.text = name
    }

    override fun getItemCount(): Int = listDessert.size
}