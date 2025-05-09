package com.example.mobileappsdg

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var userNameEditText: EditText
    private lateinit var emailEditText: EditText

    private val PICK_IMAGE_REQUEST = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile1)

        profileImageView = findViewById(R.id.profileImageView)
        userNameEditText = findViewById(R.id.userNameEditText)
        emailEditText = findViewById(R.id.emailEditText)

        // Load the saved profile data when the activity is created
        loadProfileData()

        // Save the updated profile information
        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveProfileData()
        }

        // Set a listener to choose a profile picture
        val chooseProfilePicButton: Button = findViewById(R.id.chooseProfilePicButton)
        chooseProfilePicButton.setOnClickListener {
            selectProfileImage()
        }
    }

    // Function to save profile data to SharedPreferences
    private fun saveProfileData() {
        val sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("userName", userNameEditText.text.toString())
        editor.putString("userEmail", emailEditText.text.toString())

        val imageUri = profileImageView.tag as? String
        editor.putString("profileImageUri", imageUri)

        editor.apply()

        Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()
    }

    private fun loadProfileData() {
        val sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE)

        val savedName = sharedPreferences.getString("userName", "")
        val savedEmail = sharedPreferences.getString("userEmail", "")

        val savedImageUri = sharedPreferences.getString("profileImageUri", null)

        userNameEditText.setText(savedName)
        emailEditText.setText(savedEmail)

        if (savedImageUri != null) {
            val imageUri = Uri.parse(savedImageUri)
            if (isGooglePhotosUri(imageUri)) {
                // Load image from a copy (implementation depends on how you copied it)
                // For example, if you copied it to app-specific storage:
                val copiedImageUri = Uri.fromFile(File(filesDir, "profile_image.jpg"))
                profileImageView.setImageURI(copiedImageUri)
                profileImageView.tag = copiedImageUri.toString()
            } else {
                // Load image directly (for URIs not from Google Photos)
                profileImageView.setImageURI(imageUri)
                profileImageView.tag = savedImageUri
            }
        }
    }

    private fun isGooglePhotosUri(uri: Uri): Boolean {
        return uri.authority?.contains("com.google.android.apps.photos") == true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun selectProfileImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
            // Add this line to target the Downloads directory
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()))
        }
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    @Deprecated("Deprecated")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri? = data.data
            imageUri?.let {
                // Request persistable permissions
                contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)

                profileImageView.setImageURI(it)
                profileImageView.tag = it.toString()
            }
        }
    }
}