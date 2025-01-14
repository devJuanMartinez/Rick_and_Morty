package com.example.myapplication.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService by lazy {
        retrofit.create(MyService::class.java)
    }
}