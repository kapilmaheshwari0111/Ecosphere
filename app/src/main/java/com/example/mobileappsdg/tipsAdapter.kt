package com.example.mobileappsdg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TipsAdapter(private val tipsList: List<Tip>) :
    RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tipImageView: ImageView = itemView.findViewById(R.id.tipImageView)
        val tipTextView: TextView = itemView.findViewById(R.id.tipTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val currentTip = tipsList[position]
        holder.tipImageView.setImageResource(currentTip.imageResId)
        holder.tipTextView.text = currentTip.text
    }

    override fun getItemCount(): Int {
        return tipsList.size
    }
}