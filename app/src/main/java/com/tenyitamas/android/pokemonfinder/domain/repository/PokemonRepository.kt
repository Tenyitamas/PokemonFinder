package com.tenyitamas.android.pokemonfinder.domain.repository

import com.tenyitamas.android.pokemonfinder.domain.model.PokemonDetail
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo
import com.tenyitamas.android.pokemonfinder.util.Resource

interface PokemonRepository {

    suspend fun getAllPokemon(
        limit: Int,
        offset: Int
    ) : Resource<List<PokemonInfo>>

    suspend fun getPokemonInfo(
        id: Int
    ) : Resource<PokemonDetail>
}