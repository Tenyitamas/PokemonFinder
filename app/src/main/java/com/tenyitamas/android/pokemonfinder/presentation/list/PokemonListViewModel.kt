package com.tenyitamas.android.pokemonfinder.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo
import com.tenyitamas.android.pokemonfinder.domain.use_case.GetAllPokemonUseCase
import com.tenyitamas.android.pokemonfinder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
            state = PokemonListNetworkState.Loading,
            pokemons = emptyList()
        )
        loadPokemons()
    }

    fun onEvent(event: PokemonListEvent) {
        when (event) {
            PokemonListEvent.OnLoadMore -> {
                when (state.state) {
                    is PokemonListNetworkState.Error -> loadPokemons()
                    PokemonListNetworkState.Loading -> {} // Do nothing
                    is PokemonListNetworkState.Success -> loadPokemons()
                }
            }
        }
    }

    private fun loadPokemons() {

        state = if (state.pokemons.isEmpty()) {
            state.copy(
                state = PokemonListNetworkState.Loading
            )
        } else {
            state.copy(
                state = PokemonListNetworkState.Success(
                    loadingMoreState = PokemonListNetworkState.LoadingMoreState.Loading
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
                            PokemonListNetworkState.Error(res.message)
                        } else {
                            PokemonListNetworkState.Success(
                                loadingMoreState = PokemonListNetworkState.LoadingMoreState.Error(
                                    message = res.message
                                )
                            )
                        }
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        state = if (state.pokemons.isEmpty()) {
                            PokemonListNetworkState.Loading
                        } else {
                            PokemonListNetworkState.Success(
                                loadingMoreState = PokemonListNetworkState.LoadingMoreState.Loading
                            )
                        }
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        state = PokemonListNetworkState.Success(
                            loadingMoreState = PokemonListNetworkState.LoadingMoreState.Success
                        ),
                        pokemons = state.pokemons.plus(res.data ?: emptyList())
                    )
                }
            }
        }
    }
}