package com.example.mobileappsdg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TipsActivity : AppCompatActivity() {

    private lateinit var tipsRecyclerView: RecyclerView
    private lateinit var tipsAdapter: TipsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        tipsRecyclerView = findViewById(R.id.tipsRecyclerView)
        tipsRecyclerView.layoutManager = LinearLayoutManager(this)

        val tipsData = listOf(
            Tip("Reduce food waste by planning meals and storing food properly.", R.drawable.ic_food_waste),
            Tip("Choose products with minimal packaging.", R.drawable.ic_minimal_packaging),

        )

        tipsAdapter = TipsAdapter(tipsData)
        tipsRecyclerView.adapter = tipsAdapter
    }
}

data class Tip(val text: String, val imageResId: Int)