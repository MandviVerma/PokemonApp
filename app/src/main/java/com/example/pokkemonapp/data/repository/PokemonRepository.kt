package com.example.pokkemonapp.data.repository

import com.example.pokkemonapp.data.network.PokemonApi
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val api: PokemonApi) {
    suspend fun getPokemonList() = api.getPokemonList()
    suspend fun getPokemonDetail(id: String) = api.getPokemonDetail(id)
}
