package com.tenyitamas.android.pokemonfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tenyitamas.android.pokemonfinder.navigation.Route
import com.tenyitamas.android.pokemonfinder.presentation.detail.PokemonDetailScreen
import com.tenyitamas.android.pokemonfinder.presentation.list.PokemonListScreen
import com.tenyitamas.android.pokemonfinder.ui.theme.PokemonFinderTheme
import com.tenyitamas.android.pokemonfinder.util.Constants.Companion.POKEMON_ID_PATH
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalFoundationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonFinderTheme {

                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.LIST
                    ) {
                        composable(Route.LIST) {
                            PokemonListScreen(
                                onPokemonClick = {
                                    navController.navigate(
                                        Route.DETAIL
                                            .plus("/${it.id}")
                                    )
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        composable(
                            route = Route.DETAIL
                                .plus("/{$POKEMON_ID_PATH}"),
                            arguments = listOf(
                                navArgument(POKEMON_ID_PATH) {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            PokemonDetailScreen(
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

