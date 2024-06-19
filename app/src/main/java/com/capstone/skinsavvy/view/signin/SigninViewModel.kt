package com.capstone.skinsavvy.view.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigninViewModel(val repository: com.capstone.skinsavvy.data.repository.AuthRepository<Any?>): ViewModel(){
    private val _signInResult = MutableLiveData<Boolean>()
    val signInResult: LiveData<Boolean> = _signInResult
    fun createUser(email: String,password: String) {}

}