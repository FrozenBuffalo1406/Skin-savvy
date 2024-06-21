package com.capstone.skinsavvy.view.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsavvy.MyApp
import com.capstone.skinsavvy.databinding.ActivitySignUpBinding
import com.capstone.skinsavvy.factory.AuthViewModelFactory
import com.capstone.skinsavvy.view.auth.AuthViewModel
import com.capstone.skinsavvy.view.main.MainActivity
import com.capstone.skinsavvy.view.main.MainActivity.Companion.USER_NAME
import com.capstone.skinsavvy.view.signin.SignInActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var authViewModel : AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = (application as MyApp).authRepository
        authViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(repository)
        )[AuthViewModel::class.java]

        authViewModel.registerStatus.observe(this) { isRegistered ->
            if (isRegistered) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Gagal registrasi.", Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.user.observe(this) { user ->
            if (user != null) {
                USER_NAME = user.displayName.toString()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Harap masuk terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signInButton.setOnClickListener {
            intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val displayName = binding.etName.toString()
            val email = binding.etEmail.toString()
            val password = binding.etPassword.toString()
            authViewModel.signup(email, password, displayName)
        }
    }
}