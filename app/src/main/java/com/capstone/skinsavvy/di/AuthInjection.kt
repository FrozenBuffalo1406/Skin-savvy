package com.capstone.skinsavvy.di

import android.content.Context
import com.capstone.skinsavvy.data.api.AuthConfig
import com.capstone.skinsavvy.data.pref.UserPreference
import com.capstone.skinsavvy.data.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AuthInjection {
    fun provideAuth (context : Context) : AuthRepository {
        val userPreference = UserPreference.getInstance(context)
        val user = runBlocking { userPreference.getSession().first()}
        val authApiService = AuthConfig.getApiService(user.token)
        return AuthRepository(authApiService, userPreference)
    }
}
