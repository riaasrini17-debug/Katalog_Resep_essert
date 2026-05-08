package com.example.katalog_resep_essert

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var rvDessertCatalog: RecyclerView
    private lateinit var layoutEmptyView: LinearLayout
    private lateinit var svDessert: SearchView

    // 1. Array utama untuk menyimpan "Database" lokal (Data Asli)
    private val listDessertOriginal = ArrayList<Dessert>()

    // 2. Array pendukung untuk data yang sedang ditampilkan (Hasil Search/Sort)
    private var listDessertDisplay = ArrayList<Dessert>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi View
        rvDessertCatalog = findViewById(R.id.rvDessertCatalog)
        layoutEmptyView = findViewById(R.id.layoutEmptyView)
        svDessert = findViewById(R.id.svDessert)

        // 3. Menyusun data awal ke Array
        setupData()

        // 4. Logika Searching
        svDessert.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filterSearch(newText)
                return true
            }
        })
    }

    private fun setupData() {
        listDessertOriginal.clear()
        listDessertOriginal.addAll(getDummyData())

        // --- ALGORITMA SORTING (A-Z) ---
        // Mengurutkan data secara otomatis berdasarkan nama dari A sampai Z
        Collections.sort(listDessertOriginal) { d1, d2 ->
            d1.name.compareTo(d2.name, ignoreCase = true)
        }

        // Tampilkan data ke list display
        listDessertDisplay.clear()
        listDessertDisplay.addAll(listDessertOriginal)

        showRecyclerList(listDessertDisplay)
        updateUI(listDessertDisplay)
    }

    // --- BAGIAN YANG DIPERBARUI: MEMASUKKAN FOTO ASLI ---
    private fun getDummyData(): ArrayList<Dessert> {
        val list = ArrayList<Dessert>()

        // Sekarang kita masukkan 3 data: (Nama, ID Gambar, Teks Resep)

        list.add(Dessert("Chocolate Lava Cake", R.drawable.chocolate_lava_cakes,
            "Bahan-bahan:\n- 100gr Dark Chocolate\n- 3 sdm Mentega\n- 2 Butir Telur\n- 2 sdm Gula Pasir\n- 3 sdm Tepung Terigu\n\nCara Membuat:\n1. Lelehkan coklat dan mentega, aduk rata.\n2. Kocok telur dan gula hingga larut.\n3. Masukkan lelehan coklat ke kocokan telur.\n4. Tambahkan tepung terigu, aduk rata.\n5. Tuang ke cetakan, panggang di oven 200°C selama 10-12 menit.\n6. Sajikan hangat agar coklatnya lumer!"
        ))

        list.add(Dessert("Strawberry Cheesecake", R.drawable.strawberry_cheesecake,
            "Bahan-bahan:\n- 150gr Biskuit Regal (hancurkan)\n- 50gr Mentega cair\n- 250gr Cream Cheese\n- 50gr Gula halus\n- Selai Strawberry\n\nCara Membuat:\n1. Campur biskuit dan mentega, padatkan di dasar loyang.\n2. Mixer cream cheese dan gula halus hingga lembut.\n3. Tuang ke atas lapisan biskuit.\n4. Dinginkan di kulkas minimal 4 jam.\n5. Oleskan selai strawberry di atasnya sebelum disajikan."
        ))

        list.add(Dessert("Mango Pudding", R.drawable.mango_pudding,
            "Bahan-bahan:\n- 2 buah Mangga harum manis (blender halus)\n- 1 bungkus Agar-agar plain\n- 500ml Susu cair\n- 80gr Gula pasir\n\nCara Membuat:\n1. Campurkan susu cair, agar-agar, dan gula di panci.\n2. Masak dengan api kecil sambil diaduk hingga mendidih.\n3. Matikan api, masukkan jus mangga, aduk rata.\n4. Tuang ke dalam cetakan pudding.\n5. Simpan di kulkas hingga set."
        ))

        list.add(Dessert("Tiramisu Classic", R.drawable.tiramisu,
            "Bahan-bahan:\n- 1 bungkus Ladyfingers\n- 250gr Mascarpone cheese\n- 2 kuning telur\n- Kopi espresso (diseduh)\n- Coklat bubuk\n\nCara Membuat:\n1. Mixer kuning telur dan gula hingga pucat, campurkan dengan mascarpone.\n2. Celupkan ladyfingers ke dalam kopi (jangan terlalu lama).\n3. Susun ladyfingers di wadah, lapisi dengan adonan mascarpone.\n4. Ulangi layer hingga habis.\n5. Taburi coklat bubuk di atasnya, dinginkan semalaman."
        ))

        list.add(Dessert("Macarons", R.drawable.macaron,
            "Bahan-bahan:\n- 100gr Tepung almond\n- 100gr Gula halus\n- 2 Putih telur\n- 50gr Gula kastor\n- Pewarna makanan\n\nCara Membuat:\n1. Saring tepung almond dan gula halus.\n2. Mixer putih telur hingga berbusa, masukkan gula kastor bertahap hingga kaku (meringue).\n3. Lipat campuran almond ke meringue secara perlahan (macaronage).\n4. Cetak bulat di loyang, diamkan 30 menit hingga permukaan kering.\n5. Panggang di suhu 150°C selama 15 menit."
        ))

        list.add(Dessert("Apple Pie", R.drawable.apple_pie,
            "Bahan-bahan:\n- 2 lembar Puff Pastry instan\n- 3 buah Apel (potong dadu)\n- 3 sdm Gula palem\n- 1 sdt Kayu manis bubuk\n- 1 sdm Mentega\n\nCara Membuat:\n1. Tumis apel, gula palem, mentega, dan kayu manis hingga apel layu dan air menyusut.\n2. Siapkan cetakan pie, lapisi dengan puff pastry.\n3. Tuang isian apel ke dalamnya.\n4. Tutup dengan sisa puff pastry (bentuk anyaman).\n5. Olesi kuning telur, panggang 190°C selama 25 menit."
        ))

        list.add(Dessert("Dubai Chewy Cookie", R.drawable.dubai_chuwy_cookie,
            "Bahan-bahan:\n- 150gr Tepung terigu\n- 100gr Mentega (lelehkan)\n- 80gr Gula palem\n- 1 Butir telur\n- 100gr Chocochips\n\nCara Membuat:\n1. Aduk mentega leleh dan gula palem hingga rata.\n2. Masukkan telur, aduk kembali.\n3. Masukkan tepung terigu perlahan, aduk lipat menggunakan spatula.\n4. Tambahkan chocochips.\n5. Bentuk bulat-bulat besar, letakkan di loyang.\n6. Panggang di suhu 170°C selama 12-15 menit (tengahnya masih agak soft)."
        ))

        list.add(Dessert("Pancake", R.drawable.pancake,
            "Bahan-bahan:\n- 150gr Tepung terigu\n- 2 sdm Gula pasir\n- 1 sdt Baking powder\n- 1 Butir telur\n- 200ml Susu cair\n- 2 sdm Mentega cair\n\nCara Membuat:\n1. Campur semua bahan kering (terigu, gula, baking powder).\n2. Masukkan telur dan susu cair, aduk perlahan (jangan overmix).\n3. Masukkan mentega cair, aduk rata.\n4. Panaskan teflon, tuang 1 sendok sayur adonan.\n5. Balik saat sudah bersarang/berlubang, masak hingga matang."
        ))

        list.add(Dessert("Cheesecake", R.drawable.cheseecake,
            "Bahan-bahan:\n- 250gr Cream Cheese (suhu ruang)\n- 100ml Heavy Cream\n- 2 Butir Telur\n- 80gr Gula\n- 1 sdm Tepung maizena\n\nCara Membuat:\n1. Mixer cream cheese dan gula hingga sangat lembut.\n2. Masukkan telur satu per satu sambil terus dimixer.\n3. Tuangkan heavy cream dan maizena, aduk rata.\n4. Tuang ke loyang yang sudah dialasi baking paper.\n5. Panggang dengan teknik Au Bain Marie (loyang direndam air panas) di suhu 160°C selama 50 menit."
        ))

        list.add(Dessert("Chocolate Mousse", R.drawable.chocolate_mousse,
            "Bahan-bahan:\n- 150gr Dark Chocolate (lelehkan)\n- 200ml Whipping Cream cair (dingin)\n- 2 sdm Gula halus\n\nCara Membuat:\n1. Mixer whipping cream dan gula halus hingga kaku (stiff peaks).\n2. Masukkan coklat leleh (pastikan sudah suhu ruang, tidak panas) sedikit demi sedikit ke dalam krim.\n3. Aduk lipat menggunakan spatula hingga rata.\n4. Tuang ke gelas saji.\n5. Dinginkan di kulkas minimal 2 jam."
        ))

        list.add(Dessert("Croffle", R.drawable.croffle,
            "Bahan-bahan:\n- Adonan Croissant instan (frozen)\n- Gula pasir secukupnya\n- Mentega (untuk olesan cetakan)\n\nCara Membuat:\n1. Diamkan adonan croissant frozen di suhu ruang hingga agak mengembang (sekitar 30 menit).\n2. Gulingkan adonan ke dalam gula pasir hingga seluruh permukaannya tertutup.\n3. Panaskan cetakan waffle, olesi mentega tipis-tipis.\n4. Panggang croissant di cetakan waffle hingga kecoklatan dan karamel gulanya terbentuk."
        ))

        list.add(Dessert("Japanese Souffle Pancake", R.drawable.japanese_pancake,
            "Bahan-bahan:\n- 2 Kuning telur\n- 2 Putih telur\n- 30ml Susu cair\n- 30gr Tepung terigu\n- 2 sdm Gula pasir\n- 1/2 sdt Vanilla extract\n\nCara Membuat:\n1. Aduk kuning telur, susu, dan tepung terigu hingga rata (adonan pasta).\n2. Di wadah lain, mixer putih telur hingga berbusa, masukkan gula bertahap hingga kaku (meringue).\n3. Campur meringue ke adonan pasta secara bertahap dengan teknik aduk lipat.\n4. Panaskan teflon api sangat kecil. Tuang adonan menumpuk ke atas.\n5. Tutup teflon, masak 5 menit, balik, masak lagi 5 menit."
        ))

        return list
    }

    // --- LOGIKA SEARCHING DENGAN LOGCAT & TRY-CATCH ---
    private fun filterSearch(query: String?) {
        try {
            val searchText = query?.lowercase(Locale.getDefault()) ?: ""
            Log.d("SEARCH_LOG", "User sedang mencari: $searchText")

            listDessertDisplay = ArrayList()

            if (searchText.isNotEmpty()) {
                for (item in listDessertOriginal) {
                    if (item.name.lowercase(Locale.getDefault()).contains(searchText)) {
                        listDessertDisplay.add(item)
                    }
                }
            } else {
                listDessertDisplay.addAll(listDessertOriginal)
            }

            updateUI(listDessertDisplay)
            showRecyclerList(listDessertDisplay)

        } catch (e: Exception) {
            Log.e("SEARCH_ERROR", "Kesalahan saat memfilter: ${e.message}")
        }
    }

    private fun updateUI(currentList: List<Dessert>) {
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