package com.example.mobileappsdg

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChallengesAdapter(
    private val challengesList: List<Challenge>,
    private val onUploadClick: (Int) -> Unit
) : RecyclerView.Adapter<ChallengesAdapter.ChallengeViewHolder>() {

    class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val challengeNameTextView: TextView = itemView.findViewById(R.id.challengeNameTextView)
        val challengeProgressBar: ProgressBar = itemView.findViewById(R.id.challengeProgressBar)
        val completedCountTextView: TextView = itemView.findViewById(R.id.completedCountTextView)
        val minusButton: Button = itemView.findViewById(R.id.minusButton)
        val plusButton: Button = itemView.findViewById(R.id.plusButton)
        val challengeImageView: ImageView = itemView.findViewById(R.id.challengeImageView)
        val uploadImageButton: Button = itemView.findViewById(R.id.uploadImageButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_challenge, parent, false)
        return ChallengeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val currentChallenge = challengesList[position]
        holder.challengeNameTextView.text = currentChallenge.name
        holder.completedCountTextView.text =
            "${currentChallenge.completedCount}/${currentChallenge.totalCount}"
        holder.challengeProgressBar.max = currentChallenge.totalCount
        holder.challengeProgressBar.progress = currentChallenge.completedCount

        // Handle minus button click
        holder.minusButton.setOnClickListener {
            if (currentChallenge.completedCount > 0) {
                currentChallenge.completedCount--
                notifyItemChanged(position)
            }
        }

        // Handle plus button click
        holder.plusButton.setOnClickListener {
            if (currentChallenge.completedCount < currentChallenge.totalCount) {
                currentChallenge.completedCount++
                notifyItemChanged(position)
            }
        }

        // Handle upload button click
        holder.uploadImageButton.setOnClickListener {
            onUploadClick(position)
        }

        // Show the selected image if available
        currentChallenge.imageUri?.let {
            holder.challengeImageView.setImageURI(it)
            holder.challengeImageView.visibility = View.VISIBLE
        } ?: run {
            holder.challengeImageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = challengesList.size
}