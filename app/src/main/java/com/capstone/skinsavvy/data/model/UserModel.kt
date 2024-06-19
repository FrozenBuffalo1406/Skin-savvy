package com.capstone.skinsavvy.data.model

data class UserModel(
    val email: String,
    val token: String,
    val isSignin: Boolean = false
)
