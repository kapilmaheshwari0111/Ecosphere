package com.example.mobileappsdg

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class tips : AppCompatActivity() {

    private lateinit var tipsRecyclerView: RecyclerView
    private lateinit var tipsAdapter: TipsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tips)

        // Edge-to-Edge handling for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tipsRecyclerView = findViewById(R.id.tipsRecyclerView)
        tipsRecyclerView.layoutManager = LinearLayoutManager(this)

        val tipsData = listOf(
                Tip("Reduce food waste by planning meals and storing food properly.", R.drawable.ic_food_waste),
            Tip("Choose products with minimal packaging.", R.drawable.ic_minimal_packaging),
            Tip("Use reusable bags and containers when shopping.", R.drawable.ic_reusable_bag),
            Tip("Compost food scraps and yard waste.", R.drawable.ic_compost),
            Tip("Recycle paper, plastic, glass, and metal.", R.drawable.ic_recycle),
            Tip("Conserve water by taking shorter showers and fixing leaks.", R.drawable.ic_conserve_water),
            Tip("Use energy-efficient appliances and light bulbs.", R.drawable.ic_energy_efficient),
            Tip("Walk, bike, or use public transportation whenever possible.", R.drawable.ic_transportation),
            Tip("Plant trees and support reforestation efforts.", R.drawable.ic_plant_trees),
            Tip("Educate others about sustainability and encouragethem to make changes.", R.drawable.ic_educate),
        // Add even more tips as needed
        )

        // Set the adapter
        tipsAdapter = TipsAdapter(tipsData)
        tipsRecyclerView.adapter = tipsAdapter
    }
}
