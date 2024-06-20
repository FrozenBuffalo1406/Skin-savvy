package com.capstone.skinsavvy.view.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.skinsavvy.data.model.UserModel
import com.capstone.skinsavvy.data.repository.AuthRepository
import com.capstone.skinsavvy.data.response.SigninResponse
import kotlinx.coroutines.launch

class SigninViewModel(private val repository: AuthRepository): ViewModel(){
    private val _signInResult = MutableLiveData<Boolean>()
    val signInResult: LiveData<Boolean> = _signInResult

    private val _loginResult = MutableLiveData<SigninResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _signinStatus = MutableLiveData<SigninStatus>()
    val signinStatus: LiveData<SigninStatus> get() = _signinStatus

    sealed class SigninStatus {
        data class NetworkError(val message: String) : SigninStatus()
        data class Success(val message: String) : SigninStatus()
        data class Error(val message: String): SigninStatus()
    }

    fun signin(email : String, password : String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.signin(email, password)
                _loginResult.value = response
                if (response.error == false) {
                    val token = response.signinResult?.token
                    if (token != null) {
                        val userModel = UserModel(email, token, true)
                        saveSession(userModel)
                        _signinStatus.value = SigninStatus.Success(response.message ?: "Logged in successfully")
                    } else {
                        _signinStatus.value = SigninStatus.Error("Token is null")
                    }
                } else {
                    _signinStatus.value = SigninStatus.Error(response.message ?: "Failed to log in")
                }
            } catch (e: Exception) {
                val networkErrorMessage = "Network error occurred"
                _signinStatus.value = SigninStatus.NetworkError(e.message ?: networkErrorMessage)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

}