package com.capstone.skinsavvy

import android.app.Application
import com.capstone.skinsavvy.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class MyApp : Application() {
    lateinit var authRepository: AuthRepository
    override fun onCreate() {
        super.onCreate()
        val firebaseAuth = FirebaseAuth.getInstance()
        authRepository = AuthRepository(firebaseAuth)

    }
}