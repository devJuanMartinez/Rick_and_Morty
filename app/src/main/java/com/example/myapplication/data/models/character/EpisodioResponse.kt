package com.example.myapplication.data.models.character


import com.google.gson.annotations.SerializedName

data class EpisodioResponse(
    @SerializedName("info")
    val info: InfoX?,
    @SerializedName("results")
    val results: List<Result>?
)