package com.example.katalog_resep_essert

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DessertAdapter(private val listDessert: List<Dessert>) :
    RecyclerView.Adapter<DessertAdapter.ListViewHolder>() {

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
        val dessert = listDessert[position]
        holder.imgPhoto.setImageResource(dessert.imageRes)
        holder.tvName.text = dessert.name

        // --- INI KODE NAVIGASI (INTENT) YANG HILANG TADI, BOZZ ---
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            // Validasi Sederhana: Pastikan data yang mau dikirim nggak kosong
            if (dessert.name.isNotEmpty()) {
                val intent = Intent(context, DetailActivity::class.java)

                // Kirim data ke DetailActivity
                intent.putExtra("EXTRA_NAME", dessert.name)
                intent.putExtra("EXTRA_IMAGE", dessert.imageRes)

                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = listDessert.size
}