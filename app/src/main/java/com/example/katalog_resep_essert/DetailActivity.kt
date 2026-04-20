package com.example.katalog_resep_essert

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. HUBUNGKAN TOOLBAR (Penting karena tema kamu NoActionBar)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarDetail)
        setSupportActionBar(toolbar)

        // 2. AKTIFKAN TOMBOL BACK
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Resep"

        // 3. AMBIL DATA DARI INTENT (Dari halaman utama)
        val name = intent.getStringExtra("EXTRA_NAME")
        val image = intent.getIntExtra("EXTRA_IMAGE", 0)

        // 4. HUBUNGKAN KE VIEW
        val tvDetailName = findViewById<TextView>(R.id.tvDetailName)
        val imgDetail = findViewById<ImageView>(R.id.imgDetail)

        // 5. TAMPILKAN DATA
        tvDetailName.text = name
        imgDetail.setImageResource(image)
    }

    // --- FUNGSI AGAR TOMBOL BACK BISA DIKLIK ---
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}