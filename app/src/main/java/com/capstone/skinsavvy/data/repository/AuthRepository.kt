package com.capstone.skinsavvy.data.repository

import com.capstone.skinsavvy.data.api.AuthService
import com.capstone.skinsavvy.data.model.UserModel
import com.capstone.skinsavvy.data.pref.AuthPreference
import com.capstone.skinsavvy.data.response.SigninResponse
import com.capstone.skinsavvy.data.response.SignupResponse
import retrofit2.HttpException

class AuthRepository(private val authService : AuthService, private val authPreference: AuthPreference) {
    suspend fun signup(name: String, email: String, password: String): SignupResponse {
        return authService.signup(name, email, password)
    }
    suspend fun signin(email: String, password: String): SigninResponse {
        return try {
            val response = authService.signin(email, password)
            if (!response.error!!) {
                response.signinResult?.token?.let { token ->
                    authPreference.saveSession(UserModel(email, token, true))
                }
            }
            response
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> SigninResponse(error = true, message = "Invalid email or password")
                else -> SigninResponse(error = true, message = "Failed to log in: ${e.message()}")
            }
        } catch (e: Exception) {
            SigninResponse(error = true, message = "Network error: ${e.message}")
        }
    }
    suspend fun saveSession(userModel: UserModel) {
        authPreference.saveSession(userModel)
    }
    companion object {
        fun getInstance(authService: AuthService, authPreference: AuthPreference) = AuthRepository(authService, authPreference)
    }

}
