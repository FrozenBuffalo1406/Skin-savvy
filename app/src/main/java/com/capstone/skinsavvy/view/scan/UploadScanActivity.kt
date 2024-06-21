package com.capstone.skinsavvy.view.scan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.skinsavvy.databinding.ActivityScanUploadBinding

class UploadScanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityScanUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
