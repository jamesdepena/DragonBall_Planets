package edu.ucne.dragonball_planets.presentation.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.dragonball_planets.data.remote.Resource
import edu.ucne.dragonball_planets.domain.character.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListCharacterUiState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: ListCharacterUiEvent) {
        when (event) {
            is ListCharacterUiEvent.UpdateFilters -> {
                _state.update {
                    it.copy(
                        filterName = event.name,
                        filterGender = event.gender,
                        filterRace = event.race,
                    )
                }
                loadCharacters()
            }
            ListCharacterUiEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val current = _state.value

            getCharactersUseCase(
                name = current.filterName.takeIf { it.isNotBlank() }
            ).collect { result ->

                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success -> {
                        val baseCharacters = result.data ?: emptyList()

                        val filteredCharacters = baseCharacters.filter { character ->
                            val matchGender = current.filterGender.isBlank() ||
                                    character.gender.contains(current.filterGender, ignoreCase = true)

                            val matchRace = current.filterRace.isBlank() ||
                                    character.race.contains(current.filterRace, ignoreCase = true)

                            matchGender && matchRace
                        }

                        _state.update {
                            it.copy(
                                isLoading = false,
                                characters = filteredCharacters
                            )
                        }
                    }

                    is Resource.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}