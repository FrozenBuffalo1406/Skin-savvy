package com.capstone.skinsavvy.view.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.skinsavvy.databinding.ActivitySignInBinding
import com.capstone.skinsavvy.view.auth.AuthViewModel
import com.capstone.skinsavvy.view.main.MainActivity
import com.capstone.skinsavvy.view.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignInBinding
    private val authViewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        authViewModel.loginStatus.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUpButton.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.toString()
            val password = binding.etPassword.toString()
            authViewModel.signin(email, password)
        }
    }
}