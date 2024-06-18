package com.capstone.skinsavvy.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsavvy.data.repository.AuthRepository
import com.capstone.skinsavvy.di.AuthInjection
import com.capstone.skinsavvy.view.signin.SignInViewModel
import com.capstone.skinsavvy.view.signup.SignUpViewModel


class AuthViewModelFactory (private val repository: AuthRepository) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown Viewmodel Class : " + modelClass.name)
        }
    }

    companion object {
        fun getInstance (context: Context) = AuthViewModelFactory(AuthInjection.provideAuth(context))
    }
}