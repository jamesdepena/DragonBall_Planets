package edu.ucne.dragonball_planets.presentation.detail

import edu.ucne.dragonball_planets.domain.character.model.Character

data class DetailCharacterUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null,
)