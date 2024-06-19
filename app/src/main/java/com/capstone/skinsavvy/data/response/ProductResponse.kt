package com.capstone.skinsavvy.data.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ProductResponse(

    @field:SerializedName("ListProduct")
    val listProduct : List<ListProductItem?>? = emptyList(),

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

class ListProductItem (

    @field:SerializedName("photoUrl")
    val photoUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @PrimaryKey
    @field:SerializedName("id")
    val id: String,
)
