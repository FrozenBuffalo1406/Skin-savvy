package com.capstone.skinsavvy.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.capstone.skinsavvy.MyApp
import com.capstone.skinsavvy.R
import com.capstone.skinsavvy.databinding.ActivityMainBinding
import com.capstone.skinsavvy.factory.AuthViewModelFactory
import com.capstone.skinsavvy.view.auth.AuthViewModel
import com.capstone.skinsavvy.view.history.HistoryFragment
import com.capstone.skinsavvy.view.home.HomeFragment
import com.capstone.skinsavvy.view.scan.UploadScanActivity
import com.capstone.skinsavvy.view.signin.SignInActivity

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var bottomNavigation: BottomNavigationView

    private lateinit var authViewModel: AuthViewModel

    private val fragments: Array<Fragment> = arrayOf(
        HomeFragment(),
        HistoryFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as MyApp).authRepository

        // Create the ViewModel using the factory
        authViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(repository)
        )[AuthViewModel::class.java]

        authViewModel.getCurrentUser()
        authViewModel.user.observe(this) { user ->
            if (user != null) {
                USER_NAME = user.displayName.toString()
                binding.btnScan.setOnClickListener {
                    startActivity(Intent(this@MainActivity,
                        UploadScanActivity::class.java))
                }
            } else {
                Toast.makeText(this, "Harap masuk terlebih dahulu",
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,
                    SignInActivity::class.java))
            }
        }

        fragmentContainer = binding.fragmentContainer
        bottomNavigation = binding.bottomNavigation

        bottomNavigation.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.navigation_home -> fragments[0]
                R.id.navigation_history -> fragments[2]
                else -> return@setOnItemSelectedListener false
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    companion object {
        var USER_NAME = "SAPPA"
    }
}