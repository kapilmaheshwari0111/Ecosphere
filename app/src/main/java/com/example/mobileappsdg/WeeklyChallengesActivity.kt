package com.example.mobileappsdg

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WeeklyChallengesActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_IMAGE_PICK = 1001 // Define the constant here
    }

    private lateinit var challengesRecyclerView: RecyclerView
    private lateinit var challengesAdapter: ChallengesAdapter
    private val challengesList = mutableListOf<Challenge>()
    private var selectedChallengePosition: Int = -1 // Track the clicked item's position

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_challenges)

        challengesRecyclerView = findViewById(R.id.challengesRecyclerView)
        challengesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize challenge data
        challengesList.addAll(
            listOf(
                Challenge("Use reusable bags", 0, 7),
                Challenge("Bring your own water bottle", 0, 7),
                Challenge("Reduce food waste", 0, 7),
                Challenge("Buy local produce", 0, 7),
                Challenge("Walk or bike instead of driving", 0, 7),
                Challenge("Take shorter showers", 0, 7),
                Challenge("Turn off lights when leaving a room", 0, 7),
                Challenge("Unplug electronics when not in use", 0, 7),
                Challenge("Use energy-efficient appliances", 0, 7),
                Challenge("Plant a tree", 0, 7),
                Challenge("Recycle properly", 0, 7),
                Challenge("Compost food scraps", 0, 7),
                Challenge("Avoid single-use plastics", 0, 7),
                Challenge("Choose sustainable products", 0, 7),
                Challenge("Support eco-friendly businesses", 0, 7),
                Challenge("Educate others about sustainability", 0, 7)
            )
        )

        // Pass click listener to adapter
        challengesAdapter = ChallengesAdapter(challengesList) { position ->
            selectedChallengePosition = position
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        challengesRecyclerView.adapter = challengesAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            if (selectedChallengePosition != -1 && imageUri != null) {
                challengesList[selectedChallengePosition].imageUri = imageUri
                challengesAdapter.notifyItemChanged(selectedChallengePosition) // Update only the clicked item
            }
        }
    }
}




data class Challenge(
    val name: String,
    var completedCount: Int,
    val totalCount: Int,
    var imageUri: Uri? = null
)