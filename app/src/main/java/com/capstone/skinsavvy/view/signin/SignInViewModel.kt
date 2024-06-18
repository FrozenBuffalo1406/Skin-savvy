package com.capstone.skinsavvy.view.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.skinsavvy.data.repository.AuthRepository

class SignInViewModel(private val repository: AuthRepository): ViewModel() {

    private val _loginResult = MutableLiveData<SignInResponse>()


}