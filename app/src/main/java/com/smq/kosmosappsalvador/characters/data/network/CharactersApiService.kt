package com.smq.kosmosappsalvador.characters.data.network

import retrofit2.http.GET

interface CharactersApiService {
    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}