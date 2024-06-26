package com.capstone.skinsavvy.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.skinsavvy.data.model.User
import com.capstone.skinsavvy.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> = _loginStatus

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean> = _registerStatus

    fun signin(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signin(email, password)
            _user.value = result.toUser()
            _loginStatus.value = result != null
        }
    }

    fun signup(email: String, password: String, displayName: String) {
        viewModelScope.launch {
            val result = repository.signup(email, password, displayName)
            _user.value = result?.toUser()
            _registerStatus.value = result != null
        }
    }

    fun getCurrentUser() {
        _user.value = repository.getCurrentUser()
    }

    fun logout() {
        repository.logout()
        _user.value = null
    }
    private fun FirebaseUser?.toUser(): User? {
        return this?.let {
            User(
                uid = uid,
                displayName = displayName,
                email = email,
                photoUrl = photoUrl?.toString()
            )
        }
    }
}