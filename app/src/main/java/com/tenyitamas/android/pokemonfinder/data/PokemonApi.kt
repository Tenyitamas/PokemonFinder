package com.tenyitamas.android.pokemonfinder.data

import com.tenyitamas.android.pokemonfinder.data.response.PokemonDetailResponse
import com.tenyitamas.android.pokemonfinder.data.response.PokemonInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    companion object {
        const val DEFAULT_LIMIT = 20
    }
    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("limit")
        limit: Int = DEFAULT_LIMIT,
        @Query("offset")
        offset: Int
    ) : Response<PokemonInfoResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id")
        id: Int
    ) : Response<PokemonDetailResponse>
}