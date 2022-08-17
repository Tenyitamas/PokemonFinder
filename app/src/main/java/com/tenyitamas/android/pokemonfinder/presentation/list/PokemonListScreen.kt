package com.tenyitamas.android.pokemonfinder.presentation.list

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tenyitamas.android.pokemonfinder.R
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo
import com.tenyitamas.android.pokemonfinder.presentation.list.components.OnBottomReached
import com.tenyitamas.android.pokemonfinder.presentation.list.components.PokemonListItem
import com.tenyitamas.android.pokemonfinder.ui.theme.LocalSpacing

@ExperimentalFoundationApi
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
    onPokemonClick: (PokemonInfo) -> Unit
) {

    val spacing = LocalSpacing.current
    val state = viewModel.state
    val listState = rememberLazyListState()

    listState.OnBottomReached {
        viewModel.onEvent(PokemonListEvent.OnLoadMore)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceSmall)
    ) {
        when (state.state) {
            is PokemonListNetworkState.Error -> {
                Text(
                    text = state.state.message ?: stringResource(id = R.string.unknown_error),
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.align(Alignment.Center)
                )

                Button(
                    onClick = { viewModel.onEvent(PokemonListEvent.OnLoadMore) },
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
            PokemonListNetworkState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Green
                )
            }
            is PokemonListNetworkState.Success -> {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    state = listState,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(spacing.spaceSmall)
                ) {
                    items(state.pokemons) { pokemon ->
                        PokemonListItem(
                            pokemon = pokemon,
                            onClick = { onPokemonClick(pokemon) }
                        )
                    }
                }

                when (state.state.loadingMoreState) {
                    is PokemonListNetworkState.LoadingMoreState.Error -> {
                        Toast.makeText(
                            LocalContext.current,
                            state.state.loadingMoreState.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    PokemonListNetworkState.LoadingMoreState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(spacing.spaceSmall),
                            color = Color.Green
                        )
                    }
                    PokemonListNetworkState.LoadingMoreState.Success -> {
                        // Do nothing
                    }
                }
            }
        }
    }


}