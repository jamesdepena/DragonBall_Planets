package edu.ucne.dragonball_planets.presentation.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.dragonball_planets.presentation.character.list.ListCharacterScreen
import edu.ucne.dragonball_planets.presentation.detail.DetailCharacterScreen
import edu.ucne.dragonball_planets.presentation.planet.detail.DetailPlanetScreen
import edu.ucne.dragonball_planets.presentation.planet.list.ListPlanetScreen
import kotlinx.coroutines.launch

@Composable
fun NavHost(
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    DrawerMenu(
        drawerState = drawerState,
        navHostController = navHostController
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Screen.PlanetList
        ) {
            composable<Screen.PlanetList> {
                ListPlanetScreen(
                    onDrawer = { scope.launch { drawerState.open() } },
                    onPlanetClick = { planetId ->
                        navHostController.navigate(Screen.PlanetDetail(planetId))
                    }
                )
            }

            composable<Screen.PlanetDetail> {
                DetailPlanetScreen(
                    onDrawer = { scope.launch { drawerState.open() } },
                    onBack = { navHostController.navigateUp() }
                )
            }

            composable<Screen.CharacterList> {
                ListCharacterScreen(
                    onDrawer = { scope.launch { drawerState.open() } },
                    onCharacterClick = { characterId ->
                        navHostController.navigate(Screen.CharacterDetail(characterId))
                    }
                )
            }

            composable<Screen.CharacterDetail> {
                DetailCharacterScreen(
                    onDrawer = { scope.launch { drawerState.open() } },
                    onBack = { navHostController.navigateUp() }
                )
            }
        }
    }
}