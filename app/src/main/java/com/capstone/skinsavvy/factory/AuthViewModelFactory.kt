package com.capstone.skinsavvy.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsavvy.data.repository.AuthRepository
import com.capstone.skinsavvy.di.AuthInjection
import com.capstone.skinsavvy.view.signin.SigninViewModel
import com.capstone.skinsavvy.view.signup.SignupViewModel


class AuthViewModelFactory (private val repository: AuthRepository) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SigninViewModel::class.java) -> {
                SigninViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(repository) as T
            }
                else -> throw IllegalArgumentException("Unknown Viewmodel Class : "+ modelClass.name)
            }
        }
    companion object {
        fun getInstance (context: Context) = AuthViewModelFactory(AuthInjection.provideAuth(context))
    }
}