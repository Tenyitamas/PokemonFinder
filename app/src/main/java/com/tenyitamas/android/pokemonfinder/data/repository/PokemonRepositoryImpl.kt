package com.tenyitamas.android.pokemonfinder.data.repository

import com.tenyitamas.android.pokemonfinder.data.PokemonApi
import com.tenyitamas.android.pokemonfinder.data.mapper.toPokemonDetail
import com.tenyitamas.android.pokemonfinder.data.mapper.toPokemonDetailDto
import com.tenyitamas.android.pokemonfinder.data.mapper.toPokemonInfo
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonDetail
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo
import com.tenyitamas.android.pokemonfinder.domain.repository.PokemonRepository
import com.tenyitamas.android.pokemonfinder.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
): PokemonRepository {
    override suspend fun getAllPokemon(limit: Int, offset: Int): Resource<List<PokemonInfo>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllPokemon(limit, offset)

                val result = response.body()
                if(response.isSuccessful && result != null) {
                    Resource.Success(result.results?.map {
                        it.toPokemonInfo()
                    })
                } else {
                    Resource.Error("Error while fetching pokemons with offset: $offset")
                }
            } catch (e: Exception) {
                Resource.Error("Error occurred: ${e.localizedMessage}")
            }
        }
    }

    override suspend fun getPokemonInfo(id: Int): Resource<PokemonDetail> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPokemonDetail(id)

                val result = response.body()
                if(response.isSuccessful && result != null) {
                    Resource.Success(result.toPokemonDetailDto().toPokemonDetail())
                } else {
                    Resource.Error("Error while fetching pokemon with id: $id")
                }
            } catch(e: Exception) {
                Resource.Error("Error occurred: ${e.localizedMessage}")
            }
        }
    }
}