package edu.ucne.dragonball_planets.presentation.character.list

import edu.ucne.dragonball_planets.domain.character.model.Character

data class ListCharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterGender: String = "",
    val filterRace: String = "",
)