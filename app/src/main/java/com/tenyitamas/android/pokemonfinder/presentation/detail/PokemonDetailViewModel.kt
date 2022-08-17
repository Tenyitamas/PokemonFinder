package com.tenyitamas.android.pokemonfinder.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonDetail
import com.tenyitamas.android.pokemonfinder.domain.use_case.GetPokemonInfoUseCase
import com.tenyitamas.android.pokemonfinder.util.Constants.Companion.POKEMON_ID_PATH
import com.tenyitamas.android.pokemonfinder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonInfo: GetPokemonInfoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(PokemonDetailState())
        private set

    private val pokemonId by lazy {
        savedStateHandle.get<Int>(POKEMON_ID_PATH) ?: 1
    }

    init {
        refreshPokemon(pokemonId)
    }

    fun onRefresh() {
        refreshPokemon(pokemonId)
    }

    private fun refreshPokemon(id: Int) {
        state = state.copy(
            state = PokemonDetailNetworkState.Loading
        )

        viewModelScope.launch {
            var res: Resource<PokemonDetail>

            withContext(Dispatchers.IO) {
                res = getPokemonInfo(id)
            }

            when(res) {
                is Resource.Error -> {
                    state = state.copy(
                        state = PokemonDetailNetworkState.Error(res.message)
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        state = PokemonDetailNetworkState.Loading
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        state = PokemonDetailNetworkState.Success,
                        pokemon = res.data
                    )
                }
            }
        }
    }
}