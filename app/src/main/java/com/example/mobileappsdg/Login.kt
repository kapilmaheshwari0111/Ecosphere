package com.example.mobileappsdg

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class Login : AppCompatActivity() {

    lateinit var gso: GoogleSignInOptions
    lateinit var gsc: GoogleSignInClient
    lateinit var googleBtn: ImageView
    lateinit var loginBtn: Button
    lateinit var registerBtn: Button
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences

    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        } else {
            Toast.makeText(this, "Sign-in failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // Initialize GoogleSignInOptions
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Initialize GoogleSignInClient
        gsc = GoogleSignIn.getClient(this, gso)

        // Set up the Google sign-in button
        googleBtn = findViewById(R.id.google_btn)
        googleBtn.setOnClickListener {
            signInWithGoogle()
        }

        // Initialize manual login views
        loginBtn = findViewById(R.id.loginbtn)
        registerBtn = findViewById(R.id.registerbtn)  // New register button
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)

        // Handle manual login button click
        loginBtn.setOnClickListener {
            manualLogin()
        }

        // Handle registration button click
        registerBtn.setOnClickListener {
            startActivity(Intent(this, SetPasswordActivity::class.java))
        }
    }

    // Google Sign-In flow
    private fun signInWithGoogle() {
        val signInIntent = gsc.signInIntent
        signInLauncher.launch(signInIntent)
    }

    // Handle Google sign-in result
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                // Successfully signed in with Google, redirect to the HomeActivity
                val intent = Intent(this, homepage::class.java)
                intent.putExtra("userName", account.email) // Use email as the username
                startActivity(intent)
                finish()
            }
        } catch (e: ApiException) {
            Log.e("SignInError", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(this, "Google sign-in failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Manual login flow for email/username and password
    private fun manualLogin() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Validate username and password
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
        } else {
            // Retrieve stored credentials from SharedPreferences
            val storedPassword = sharedPreferences.getString(username, null)

            // Check if the stored password matches the entered password
            if (storedPassword != null && storedPassword == password) {
                // Login successful, redirect to HomeActivity
                val intent = Intent(this, homepage::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
