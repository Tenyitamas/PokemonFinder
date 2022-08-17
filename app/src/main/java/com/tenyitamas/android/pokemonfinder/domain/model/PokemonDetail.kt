package com.tenyitamas.android.pokemonfinder.domain.model

data class PokemonDetail(
    val pokemonInfo: PokemonInfo,
    val weight: Int,
    val height: Int,
    val shownAbilities: List<PokemonAbility>
)
