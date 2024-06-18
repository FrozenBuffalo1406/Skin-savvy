package com.capstone.skinsavvy.data.repository

class AuthRepository {
    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return authService.register(name, email, password)
    }

    suspend fun saveSession(userModel: UserModel) {
        userPreference.saveSession(userModel)
    }

    companion object {
        fun getInstance(authService: AuthService, userPreference: UserPreference) = AuthRepository(authService, userPreference)
    }

}