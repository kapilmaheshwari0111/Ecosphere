package com.example.mobileappsdg
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class homepage : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.itemIconTintList = null
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var downloadcard = findViewById<CardView>(R.id.downloadCard)
        downloadcard.setOnClickListener() {
            val intent = Intent(this, tips::class.java)
            startActivity(intent)
        }
        var imageCard = findViewById<CardView>(R.id.imageCard)
        imageCard.setOnClickListener() {
            val intent = Intent(this, NearbyStoresActivity::class.java)
            startActivity(intent)
        }
        var audioCard = findViewById<CardView>(R.id.audioCard)
        audioCard.setOnClickListener() {
            val intent = Intent(this, WeeklyChallengesActivity::class.java)
            startActivity(intent)
        }
        var docCard = findViewById<CardView>(R.id.docCard)
        docCard.setOnClickListener() {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }
        var videoCard = findViewById<CardView>(R.id.videoCard)
        videoCard.setOnClickListener() {
            val intent = Intent(this, ResourceCalculator::class.java)
            startActivity(intent)
        }
        var appCard = findViewById<CardView>(R.id.appCard)
        appCard.setOnClickListener() {
            val intent = Intent(this, Achievements::class.java)
            startActivity(intent)
        }
        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItemSelected(menuItem)
            drawerLayout.closeDrawers() // Close the drawer after selection
            true
        }
    }

    private fun handleNavigationItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile Selected", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_about_us -> {
                val intent = Intent(this, AboutUs::class.java)
                startActivity(intent)
            }

            R.id.nav_logout -> {
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
                // Navigate to Login activity
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

            R.id.nav_contact_us -> {
                val intent = Intent(this, ContactUs::class.java)
                startActivity(intent)
            }

            R.id.nav_chatter_box -> {
                val intent = Intent(this, ChatBot::class.java)
                startActivity(intent)
            }
            R.id.nav_scan -> {
                startActivity(Intent(this, ScanActivity::class.java))
                true
            }

        }
    }
}








