package com.tenyitamas.android.pokemonfinder.data.response

import com.tenyitamas.android.pokemonfinder.data.dto.PokemonAbilityDto

data class PokemonAbilityResponse(
    val ability: PokemonAbilityDto?,
    val is_hidden: Boolean?,
    val slot: Int?
)
