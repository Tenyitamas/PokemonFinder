package com.tenyitamas.android.pokemonfinder.presentation.list

import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo



data class PokemonListState(
    val state: PokemonListNetworkState = PokemonListNetworkState.Loading,
    val pokemons: List<PokemonInfo> = emptyList()
)

sealed class PokemonListNetworkState() {
    object Loading: PokemonListNetworkState()
    data class Success(val loadingMoreState: LoadingMoreState): PokemonListNetworkState()
    data class Error(val message: String?): PokemonListNetworkState()

    sealed class LoadingMoreState {
        object Loading: LoadingMoreState()
        data class Error(val message: String?): LoadingMoreState()
        object Success: LoadingMoreState()
    }
}



