package com.capstone.skinsavvy.data.response

import com.google.gson.annotations.SerializedName

data class SigninResponse(

    @field:SerializedName("loginResult")
    val signinResult: SigninResult? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class SigninResult(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("token")
    val token: String? = null
)