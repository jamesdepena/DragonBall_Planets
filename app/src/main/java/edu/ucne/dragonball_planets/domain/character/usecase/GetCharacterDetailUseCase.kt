package edu.ucne.dragonball_planets.domain.character.usecase

import edu.ucne.dragonball_planets.domain.character.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.getCharacterDetail(id)
}