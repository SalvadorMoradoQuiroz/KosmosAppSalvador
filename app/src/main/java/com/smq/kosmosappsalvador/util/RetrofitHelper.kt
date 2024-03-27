package com.smq.kosmosappsalvador.util

import com.smq.kosmosappsalvador.characters.data.network.CharactersApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CharactersApiService = getRetrofitInstance().create(CharactersApiService::class.java)
}