package com.tenyitamas.android.pokemonfinder.presentation.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo
import com.tenyitamas.android.pokemonfinder.domain.use_case.GetAllPokemonUseCase
import com.tenyitamas.android.pokemonfinder.domain.use_case.GetPokemonInfoUseCase
import com.tenyitamas.android.pokemonfinder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getAllPokemon: GetAllPokemonUseCase
) : ViewModel() {

    var state by mutableStateOf(PokemonListState())
        private set

    init {
        state = state.copy(
            state = PokemonListSealedClassState.Loading,
            pokemons = emptyList()
        )
        loadPokemons()
    }

    fun onEvent(event: PokemonListEvent) {
        when (event) {
            PokemonListEvent.OnLoadMore -> {
                when (state.state) {
                    is PokemonListSealedClassState.Error -> loadPokemons()
                    PokemonListSealedClassState.Loading -> {} // Do nothing
                    is PokemonListSealedClassState.Success -> loadPokemons()
                }
            }
        }
    }

    private fun loadPokemons() {

        if (state.pokemons.isEmpty()) {
            state = state.copy(
                state = PokemonListSealedClassState.Loading
            )
        } else {
            state = state.copy(
                state = PokemonListSealedClassState.Success(
                    loadingMoreState = PokemonListSealedClassState.LoadingMoreState.Loading
                )
            )
        }

        viewModelScope.launch {

            var res: Resource<List<PokemonInfo>>
            val offset = state.pokemons.size
            withContext(Dispatchers.IO) {


                res = getAllPokemon(
                    limit = 20,
                    offset = offset
                )

            }
            when (res) {
                is Resource.Error -> {

                    state = state.copy(
                        state = if (state.pokemons.isEmpty()) {
                            PokemonListSealedClassState.Error(res.message)
                        } else {
                            PokemonListSealedClassState.Success(
                                loadingMoreState = PokemonListSealedClassState.LoadingMoreState.Error(
                                    message = res.message
                                )
                            )
                        }
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        state = if (state.pokemons.isEmpty()) {
                            PokemonListSealedClassState.Loading
                        } else {
                            PokemonListSealedClassState.Success(
                                loadingMoreState = PokemonListSealedClassState.LoadingMoreState.Loading
                            )
                        }
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        state = PokemonListSealedClassState.Success(
                            loadingMoreState = PokemonListSealedClassState.LoadingMoreState.Success
                        ),
                        pokemons = state.pokemons.plus(res.data ?: emptyList())
                    )
                }
            }
        }
    }
}