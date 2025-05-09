package com.example.mobileappsdg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RelatedVideosAdapter(
    private val videoData: List<VideoData>,
    private val onVideoClick: (VideoData) -> Unit
) :
    RecyclerView.Adapter<RelatedVideosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoTitle: TextView = itemView.findViewById(R.id.videoTitle)
        val playButton: ImageButton = itemView.findViewById(R.id.playButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_related_video, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videoData[position]
        holder.videoTitle.text = video.title
        holder.playButton.setOnClickListener {
            onVideoClick(video)
        }
    }

    override fun getItemCount(): Int {
        return videoData.size
    }
}