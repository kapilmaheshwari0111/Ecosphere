package com.example.mobileappsdg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoresAdapter(private val storesList: List<Store>) :
    RecyclerView.Adapter<StoresAdapter.StoreViewHolder>() {

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storeNameTextView: TextView = itemView.findViewById(R.id.storeNameTextView)
        val storeDistanceTextView: TextView = itemView.findViewById(R.id.storeDistanceTextView)
        val storeDetailsTextView: TextView = itemView.findViewById(R.id.storeDetailsTextView)
        val storeImageView: ImageView = itemView.findViewById(R.id.storeImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val currentStore = storesList[position]
        holder.storeNameTextView.text = currentStore.valname
        holder.storeDetailsTextView.text = currentStore.details
        holder.storeDistanceTextView.text = "Distance: ${currentStore.distance} km"
        holder.storeImageView.setImageResource(currentStore.imageResId)
    }

    override fun getItemCount(): Int {
        return storesList.size
    }
}