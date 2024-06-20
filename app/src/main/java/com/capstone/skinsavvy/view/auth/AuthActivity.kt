package com.capstone.skinsavvy.view.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.skinsavvy.databinding.ActivityAuthBinding
import com.capstone.skinsavvy.view.main.MainActivity
import com.capstone.skinsavvy.view.signin.SignInActivity
import com.capstone.skinsavvy.view.signup.SignUpActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.user.observe(this) { user ->
            if (user != null) {
                Toast.makeText(this, "Logged in as: ${user.email}", Toast.LENGTH_SHORT).show()
                navigateToMainScreen()
            }
        }

        authViewModel.loginStatus.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                navigateToMainScreen()
            } else {
                Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show()
            }
        }
        authViewModel.registerStatus.observe(this) { isRegistered ->
            if (isRegistered) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                navigateToMainScreen()
            } else {
                Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show()
            }
        }



        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}