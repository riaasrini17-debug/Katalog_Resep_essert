package com.example.katalog_resep_essert

import android.content.Intent
import android.util.Log
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

        // --- LOGIKA NAVIGASI DENGAN TRY-CATCH & LOGCAT ---
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            try {
                // Validasi Sederhana sebelum pindah halaman
                if (dessert.name.isNotEmpty()) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("EXTRA_NAME", dessert.name)
                        putExtra("EXTRA_IMAGE", dessert.imageRes)
                        putExtra("EXTRA_RECIPE", dessert.recipe)
                    }
                    context.startActivity(intent)

                    // Logcat untuk info keberhasilan
                    Log.i("NAV_LOG", "Berhasil pindah ke detail: ${dessert.name}")
                } else {
                    Log.w("NAV_LOG", "Data dessert kosong, navigasi dibatalkan.")
                }
            } catch (e: Exception) {
                // Logcat untuk mencatat error jika terjadi crash
                Log.e("NAV_ERROR", "Gagal pindah halaman: ${e.message}")
            }
        }
    }

    override fun getItemCount(): Int = listDessert.size
}