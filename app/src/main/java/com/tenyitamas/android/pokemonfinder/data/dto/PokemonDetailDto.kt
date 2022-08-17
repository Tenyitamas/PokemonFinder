package com.tenyitamas.android.pokemonfinder.data.dto

data class PokemonDetailDto(
    val name: String?,
    val height: Int?,
    val weight: Int?,
    val id: Int?,
    val shownAbilities: List<PokemonAbilityDto>?
)
