package com.capstone.skinsavvy.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.capstone.skinsavvy.R
import com.capstone.skinsavvy.databinding.ActivityMainBinding
import com.capstone.skinsavvy.view.history.HistoryFragment
import com.capstone.skinsavvy.view.home.HomeFragment
import com.capstone.skinsavvy.view.scan.UploadScanActivity

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var bottomNavigation: BottomNavigationView

    private val fragments: Array<Fragment> = arrayOf(
        HomeFragment(),
        HistoryFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentContainer = binding.fragmentContainer
        bottomNavigation = binding.bottomNavigation

        binding.btnScan.setOnClickListener {
            startActivity(Intent(this@MainActivity, UploadScanActivity::class.java))
        }
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
}