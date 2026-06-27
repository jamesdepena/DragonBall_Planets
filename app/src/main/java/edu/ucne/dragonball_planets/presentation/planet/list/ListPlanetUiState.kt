package edu.ucne.dragonball_planets.presentation.planet.list

import edu.ucne.dragonball_planets.domain.planet.model.Planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterIsDestroyed: Boolean? = null
)