package com.example.pokkemonapp.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokkemonapp.data.model.Pokemon
import com.example.pokkemonapp.data.model.PokemonDetail
import com.example.pokkemonapp.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    init {
        viewModelScope.launch {
            _pokemonList.value = repository.getPokemonList().results
        }
    }
}
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val pokemonId: String = savedStateHandle["pokemonId"] ?: "0"

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail> = _pokemonDetail

    init {
        viewModelScope.launch {
            _pokemonDetail.value = repository.getPokemonDetail(pokemonId)
        }
    }
}



