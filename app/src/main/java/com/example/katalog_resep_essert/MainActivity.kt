package com.example.katalog_resep_essert

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // 1. Deklarasi variabel untuk elemen UI kita
    private lateinit var rvDessertCatalog: RecyclerView
    private lateinit var layoutEmptyView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menampilkan layout activity_main.xml
        setContentView(R.layout.activity_main)

        // 2. Hubungkan variabel dengan ID yang ada di XML
        rvDessertCatalog = findViewById(R.id.rvDessertCatalog)
        layoutEmptyView = findViewById(R.id.layoutEmptyView)

        // 3. Jalankan fungsi untuk mengecek tampilan
        checkDataAndUpdateUI()
    }

    private fun checkDataAndUpdateUI() {
        // Simulasi: Anggap saja saat ini daftar resep kita masih KOSONG
        val isDataKosong = true

        if (isDataKosong) {
            // Jika kosong: Munculkan Empty View, Sembunyikan RecyclerView
            rvDessertCatalog.visibility = View.GONE
            layoutEmptyView.visibility = View.VISIBLE
        } else {
            // Jika ada isinya: Munculkan RecyclerView, Sembunyikan Empty View
            rvDessertCatalog.visibility = View.VISIBLE
            layoutEmptyView.visibility = View.GONE

            // Nanti kode untuk memasukkan data ke RecyclerView ditaruh di sini
        }
    }
}