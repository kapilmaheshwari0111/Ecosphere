package com.example.mobileappsdg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load animation
        val logo = findViewById<ImageView>(R.id.logo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate_zoom)

        // Start logo animation
        logo.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3 seconds delay
    }
}