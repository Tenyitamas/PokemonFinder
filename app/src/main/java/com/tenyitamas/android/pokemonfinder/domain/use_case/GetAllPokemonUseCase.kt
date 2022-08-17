package com.tenyitamas.android.pokemonfinder.domain.use_case

import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo
import com.tenyitamas.android.pokemonfinder.domain.repository.PokemonRepository
import com.tenyitamas.android.pokemonfinder.util.Resource

class GetAllPokemonUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0): Resource<List<PokemonInfo>> {
        return repository.getAllPokemon(limit, offset)
    }
}