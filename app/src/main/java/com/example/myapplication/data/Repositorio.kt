package com.example.myapplication.data

import com.example.myapplication.data.retrofit.RetrofitHelper

class Repositorio {

    suspend fun getCharacterByPage(page: Int) = RetrofitHelper.retrofitService.getCharacterByPage(page)

    suspend fun getEpisodes(episode: Int) = RetrofitHelper.retrofitService.getCharacterByPage(episode)

    suspend fun getCharacterEpisode(url: String) = RetrofitHelper.retrofitService.getCharacterEpisode(url)
}