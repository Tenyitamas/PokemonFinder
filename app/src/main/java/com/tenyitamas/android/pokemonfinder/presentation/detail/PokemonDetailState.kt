package com.tenyitamas.android.pokemonfinder.presentation.detail

import com.tenyitamas.android.pokemonfinder.domain.model.PokemonDetail

sealed class PokemonDetailNetworkState() {
    object Loading: PokemonDetailNetworkState()
    data class Error(val message: String?): PokemonDetailNetworkState()
    object Success: PokemonDetailNetworkState()
}

data class PokemonDetailState(
    val pokemon: PokemonDetail? = null,
    val state: PokemonDetailNetworkState = PokemonDetailNetworkState.Loading
)
