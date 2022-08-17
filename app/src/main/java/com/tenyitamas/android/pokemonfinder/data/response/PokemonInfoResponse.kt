package com.tenyitamas.android.pokemonfinder.data.response

import com.tenyitamas.android.pokemonfinder.data.dto.PokemonInfoDto

data class PokemonInfoResponse(
    val count: Int?,
    val results: List<PokemonInfoDto>?
)