package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Repositorio
import com.example.myapplication.data.models.character.Character
import com.example.myapplication.data.models.character.CharacterResponse
import com.example.myapplication.data.models.character.EpisodioResponse
import com.example.myapplication.data.models.character.Result
import kotlinx.coroutines.launch
import java.net.URL

class MyViewModel: ViewModel() {

    private val personajeLiveData = MutableLiveData<Character>()
    private val personajeepisodioLiveData = MutableLiveData<Result>()


    private val repositorio by lazy {
        Repositorio()
    }

    fun getCharactersByPage(page :Int): MutableLiveData<CharacterResponse> {
        val livedata = MutableLiveData<CharacterResponse>()

        viewModelScope.launch {
            val response = repositorio.getCharacterByPage(page)
            if (response.code() == 200){

                val characterResponse = response.body()

                characterResponse?.let {
                    livedata.postValue(it)
                }
            }
        }

        return livedata
    }

    fun setPersonaje(personaje: Character){
        personajeLiveData.value = personaje
    }

    fun getPersonaje() = personajeLiveData


    fun getCharacterEpisode(url: String): MutableLiveData<Result>{


        viewModelScope.launch {
            val response = repositorio.getCharacterEpisode(url)

            if (response.code() == 200){

                val episodio = response.body()

                episodio?.let {
                    personajeepisodioLiveData.postValue(it)
                }
            }
        }

        return personajeepisodioLiveData
    }





}