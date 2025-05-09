package com.example.mobileappsdg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NearbyStoresActivity : AppCompatActivity() {

    private lateinit var storesRecyclerView: RecyclerView
    private lateinit var storesAdapter: StoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_stores)

        storesRecyclerView = findViewById(R.id.storesRecyclerView)
        storesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch store data (replace with your actual data fetching logic)
        val storesData = listOf(
            Store("EcoMart", "Sustainable groceries and household items", 1.2, R.drawable.ic_store1),
            Store("GreenGoods", "Recycled clothing and accessories", 2.5, R.drawable.ic_store1),
            Store("RePurpose", "Upcycled furniture and home decor", 3.8, R.drawable.ic_store1),
            Store("PlanetFriendly", "Eco-friendly beauty and personal care", 4.1, R.drawable.ic_store1),
            Store("ZeroWasteShop", "Package-free and reusable products", 5.6, R.drawable.ic_store1),
            Store("The Conscious Closet", "Sustainable fashion and ethical brands", 6.3, R.drawable.ic_store1),

        )

        storesAdapter = StoresAdapter(storesData)
        storesRecyclerView.adapter = storesAdapter
    }
}

data class Store(val valname: String, val details: String, val distance: Double, val imageResId: Int)