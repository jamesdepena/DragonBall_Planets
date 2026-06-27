package edu.ucne.dragonball_planets.presentation.planet.detail

import edu.ucne.dragonball_planets.domain.planet.model.Planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)