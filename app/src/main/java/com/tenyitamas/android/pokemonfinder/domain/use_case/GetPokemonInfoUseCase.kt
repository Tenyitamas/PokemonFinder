package com.tenyitamas.android.pokemonfinder.domain.use_case

import com.tenyitamas.android.pokemonfinder.domain.model.PokemonDetail
import com.tenyitamas.android.pokemonfinder.domain.repository.PokemonRepository
import com.tenyitamas.android.pokemonfinder.util.Resource

class GetPokemonInfoUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Resource<PokemonDetail> {
        return repository.getPokemonInfo(id)
    }
}