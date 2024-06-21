package com.capstone.skinsavvy.view.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsavvy.MyApp
import com.capstone.skinsavvy.databinding.ActivitySignInBinding
import com.capstone.skinsavvy.factory.AuthViewModelFactory
import com.capstone.skinsavvy.view.auth.AuthViewModel
import com.capstone.skinsavvy.view.main.MainActivity
import com.capstone.skinsavvy.view.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignInBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as MyApp).authRepository
        authViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(repository)
        )[AuthViewModel::class.java]

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

        authViewModel.getCurrentUser()
        authViewModel.user.observe(this) { user ->
            if (user != null) {
                Toast.makeText(this, "User: ${user.displayName} (${user.email})", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Gagal membuat token.", Toast.LENGTH_SHORT).show()
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