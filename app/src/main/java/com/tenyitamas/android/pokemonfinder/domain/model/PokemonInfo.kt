package com.tenyitamas.android.pokemonfinder.domain.model

data class PokemonInfo(
    val name: String,
    val id: Int
) {
    fun getImageUrl() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
