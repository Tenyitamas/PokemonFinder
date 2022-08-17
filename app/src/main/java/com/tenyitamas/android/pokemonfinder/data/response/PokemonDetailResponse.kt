package com.tenyitamas.android.pokemonfinder.data.response

import com.tenyitamas.android.pokemonfinder.data.dto.PokemonAbilityDto

data class PokemonDetailResponse(
    val name: String?,
    val id: Int?,
    val abilities: List<PokemonAbilityResponse>?,
    val height: Int?,
    val weight: Int?,
)
