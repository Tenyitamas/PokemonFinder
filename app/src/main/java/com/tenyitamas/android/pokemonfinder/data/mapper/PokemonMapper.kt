package com.tenyitamas.android.pokemonfinder.data.mapper

import com.tenyitamas.android.pokemonfinder.data.dto.PokemonAbilityDto
import com.tenyitamas.android.pokemonfinder.data.dto.PokemonDetailDto
import com.tenyitamas.android.pokemonfinder.data.dto.PokemonInfoDto
import com.tenyitamas.android.pokemonfinder.data.response.PokemonAbilityResponse
import com.tenyitamas.android.pokemonfinder.data.response.PokemonDetailResponse
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonAbility
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonDetail
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo

fun PokemonInfoDto.toPokemonInfo() : PokemonInfo {
    return PokemonInfo(
        name = name ?: "N/A",
        id = if(url != null && url.endsWith("/")) {
            url?.dropLast(1)?.takeLastWhile { it.isDigit() }?.toInt() ?: 1
        } else {
            url?.takeLastWhile { it.isDigit() }?.toInt() ?: 1
        }
    )
}

fun PokemonAbilityDto.toPokemonAbility(): PokemonAbility {
    return PokemonAbility(
        name = name ?: "N/A",
        isHidden = isHidden ?: true
    )
}

fun PokemonDetailDto.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        pokemonInfo = PokemonInfo(
            name = name ?: "N/A",
            id = id ?: 1
        ),
        weight = weight ?: 0,
        height = height ?: 0,
        shownAbilities = shownAbilities?.filter { (it.isHidden == false) }?.map {
            it.toPokemonAbility()
        } ?: emptyList()
    )
}

fun PokemonAbilityResponse.toPokemonAbilityDto(): PokemonAbilityDto {
    return PokemonAbilityDto(
        name = ability?.name,
        isHidden = is_hidden
    )
}

fun PokemonDetailResponse.toPokemonDetailDto(): PokemonDetailDto {
    return PokemonDetailDto(
        name = name,
        height = height,
        weight = weight,
        id = id,
        shownAbilities = abilities?.filter { it.is_hidden == false }?.map {
            it.toPokemonAbilityDto()
        } ?: emptyList()
    )
}