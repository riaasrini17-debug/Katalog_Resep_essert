package com.example.katalog_resep_essert

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvDessertCatalog: RecyclerView
    private lateinit var layoutEmptyView: LinearLayout

    // List untuk menampung data
    private val list = ArrayList<Dessert>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDessertCatalog = findViewById(R.id.rvDessertCatalog)
        layoutEmptyView = findViewById(R.id.layoutEmptyView)

        // 1. Ambil data
        list.addAll(getDummyData())

        // 2. Jalankan pengecekan UI
        checkDataAndUpdateUI()
    }

    private fun getDummyData(): ArrayList<Dessert> {
        val dataName = resources.getStringArray(R.array.data_name)
        // Gunakan gambar default android dulu atau ganti dengan drawable kamu
        val dataPhoto = R.drawable.ic_launcher_background

        val listDessert = ArrayList<Dessert>()
        for (name in dataName) {
            listDessert.add(Dessert(name, dataPhoto))
        }
        return listDessert
    }

    private fun checkDataAndUpdateUI() {
        if (list.isEmpty()) {
            rvDessertCatalog.visibility = View.GONE
            layoutEmptyView.visibility = View.VISIBLE
        } else {
            rvDessertCatalog.visibility = View.VISIBLE
            layoutEmptyView.visibility = View.GONE

            showRecyclerList()
        }
    }

    private fun showRecyclerList() {
        // Karena di XML kamu pakai spanCount="4", pastikan layout manager-nya pas
        rvDessertCatalog.layoutManager = GridLayoutManager(this, 2) // Pakai 2 kolom dulu biar rapi
        val dessertAdapter = DessertAdapter(list)
        rvDessertCatalog.adapter = dessertAdapter
    }
}