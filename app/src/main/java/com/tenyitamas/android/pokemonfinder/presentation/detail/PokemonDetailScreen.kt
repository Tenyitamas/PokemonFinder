package com.tenyitamas.android.pokemonfinder.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tenyitamas.android.pokemonfinder.R
import com.tenyitamas.android.pokemonfinder.presentation.detail.components.PokemonDetailFooter
import com.tenyitamas.android.pokemonfinder.presentation.detail.components.PokemonDetailHeader
import com.tenyitamas.android.pokemonfinder.ui.theme.LocalSpacing

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val spacing = LocalSpacing.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceSmall)
    ) {
        when(state.state) {
            is PokemonDetailNetworkState.Error -> {
                Text(
                    text = state.state.message ?: stringResource(id = R.string.unknown_error),
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.align(Alignment.Center)
                )

                Button(
                    onClick = { viewModel.onRefresh() },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(spacing.spaceMedium)
                        .clip(RoundedCornerShape(50)),
                    shape = RoundedCornerShape(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Green.copy(alpha = 0.8f),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.retry),
                        color = Color.DarkGray
                    )
                }
            }
            PokemonDetailNetworkState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Green
                )
            }
            PokemonDetailNetworkState.Success -> {
                // Add poke details
                state.pokemon?.let { pokemon ->
                    Column(
                        modifier = Modifier.matchParentSize()
                    ) {
                        PokemonDetailHeader(
                            pokemon = pokemon.pokemonInfo,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3f)
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(5f)
                        ) {
                            item {
                                Text(
                                    text = stringResource(id = R.string.shown_abilities),
                                    style = MaterialTheme.typography.h3
                                )
                            }

                            items(pokemon.shownAbilities){ ability ->
                                Text(
                                    text = "    - ${ability.name}",
                                    style = MaterialTheme.typography.body1
                                )
                            }

                        }

                        PokemonDetailFooter(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            weight = pokemon.weight,
                            height = pokemon.height
                        )
                    }
                }

            }
        }
    }

}