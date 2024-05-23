package com.example.myapplication.data.retrofit

import com.example.myapplication.data.models.character.Character
import com.example.myapplication.data.models.character.CharacterResponse
import com.example.myapplication.data.models.character.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyService {

    @GET("character")
    suspend fun getCharacterByPage(
        @Query("page") page: Int
    ): Response<CharacterResponse>

    @GET("character")
    suspend fun filterByName(
        @Query("name") name: String,
        @Query("species") species: String
    ): Response<CharacterResponse>

    @GET("id")
    suspend fun getEpisodes(
        @Query("id") episode: String,
    ): Response<CharacterResponse>

    @GET()
    suspend fun getEa(
        @Query ("url") url: String,
    )

    @GET()
    suspend fun getCharacterEpisode(
        @Query ("url") url:String,
    ): Response<Result>

}