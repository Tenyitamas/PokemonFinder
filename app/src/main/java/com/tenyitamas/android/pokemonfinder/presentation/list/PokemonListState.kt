package com.tenyitamas.android.pokemonfinder.presentation.list

import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo



data class PokemonListState(
    val state: PokemonListSealedClassState = PokemonListSealedClassState.Loading,
    val pokemons: List<PokemonInfo> = emptyList()
)

sealed class PokemonListSealedClassState() {
    object Loading: PokemonListSealedClassState()
    data class Success(val loadingMoreState: LoadingMoreState): PokemonListSealedClassState()
    data class Error(val message: String?): PokemonListSealedClassState()

    sealed class LoadingMoreState {
        object Loading: LoadingMoreState()
        data class Error(val message: String?): LoadingMoreState()
        object Success: LoadingMoreState()
    }
}



