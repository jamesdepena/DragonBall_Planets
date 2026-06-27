package edu.ucne.dragonball_planets.presentation.detail

import edu.ucne.dragonball_planets.domain.model.Planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)