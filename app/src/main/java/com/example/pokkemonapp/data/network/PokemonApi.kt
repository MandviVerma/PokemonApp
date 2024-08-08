package com.example.pokkemonapp.data.network

import com.example.pokkemonapp.data.model.PokemonDetail
import com.example.pokkemonapp.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): PokemonDetail
}
