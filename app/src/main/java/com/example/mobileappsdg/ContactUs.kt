package com.example.mobileappsdg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        val contactUsButton: Button = findViewById(R.id.contact_us_button)

        // Set click listener to open the phone app with a preset phone number
        contactUsButton.setOnClickListener {
            val phoneNumber = "1234567890" // Replace with actual contact number
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(dialIntent)
        }
    }
}