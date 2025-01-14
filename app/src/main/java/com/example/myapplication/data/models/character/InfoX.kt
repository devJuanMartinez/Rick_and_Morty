package com.example.myapplication.data.models.character


import com.google.gson.annotations.SerializedName

data class InfoX(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("prev")
    val prev: Any?
)