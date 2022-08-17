package com.tenyitamas.android.pokemonfinder.presentation.list

sealed class PokemonListEvent {
    object OnLoadMore: PokemonListEvent()
}
