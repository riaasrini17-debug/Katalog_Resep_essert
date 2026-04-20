package com.example.katalog_resep_essert

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvDessertCatalog: RecyclerView
    private lateinit var layoutEmptyView: LinearLayout
    private lateinit var svDessert: SearchView

    // List untuk menampung data asli
    private val list = ArrayList<Dessert>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDessertCatalog = findViewById(R.id.rvDessertCatalog)
        layoutEmptyView = findViewById(R.id.layoutEmptyView)
        svDessert = findViewById(R.id.svDessert)

        // 1. Ambil data asli
        list.addAll(getDummyData())

        // 2. Setup RecyclerView awal
        showRecyclerList(list)

        // 3. Jalankan pengecekan UI (Empty View)
        checkDataAndUpdateUI(list)

        // 4. Logic Pencarian
        svDessert.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun getDummyData(): ArrayList<Dessert> {
        val dataName = resources.getStringArray(R.array.data_name)
        // Pastikan R.drawable.ic_launcher_background ada, atau ganti dengan gambar dessertmu
        val dataPhoto = R.drawable.ic_launcher_background

        val listDessert = ArrayList<Dessert>()
        for (name in dataName) {
            listDessert.add(Dessert(name, dataPhoto))
        }
        return listDessert
    }

    private fun filterList(query: String?) {
        val filteredList = ArrayList<Dessert>()
        for (item in list) {
            if (item.name.lowercase().contains(query.toString().lowercase())) {
                filteredList.add(item)
            }
        }

        // Update UI berdasarkan hasil filter
        checkDataAndUpdateUI(filteredList)
        showRecyclerList(filteredList)
    }

    private fun checkDataAndUpdateUI(currentList: List<Dessert>) {
        if (currentList.isEmpty()) {
            rvDessertCatalog.visibility = View.GONE
            layoutEmptyView.visibility = View.VISIBLE
        } else {
            rvDessertCatalog.visibility = View.VISIBLE
            layoutEmptyView.visibility = View.GONE
        }
    }

    private fun showRecyclerList(currentList: List<Dessert>) {
        rvDessertCatalog.layoutManager = GridLayoutManager(this, 2)
        val dessertAdapter = DessertAdapter(currentList)
        rvDessertCatalog.adapter = dessertAdapter
    }
}