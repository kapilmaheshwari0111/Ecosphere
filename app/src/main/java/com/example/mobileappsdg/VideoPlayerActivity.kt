package com.example.mobileappsdg

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var relatedVideosGrid: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoView = findViewById(R.id.videoView)
        relatedVideosGrid = findViewById(R.id.relatedVideosGrid)

        relatedVideosGrid.layoutManager = GridLayoutManager(this, 3)
        val videoData = listOf(
            VideoData("What is Sustainability (Animated)", R.raw.video1),
            VideoData("SDG 12", R.raw.video2),
            VideoData("Information", R.raw.video3)
        )
        val adapter = RelatedVideosAdapter(videoData) { video ->
            playVideo(video)
        }
        relatedVideosGrid.adapter = adapter
    }

    private fun playVideo(video: VideoData) {
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + video.videoResId)
        videoView.setVideoURI(videoUri)

        // Add video player controls
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()
    }
}

data class VideoData(val title: String, val videoResId: Int)