package edu.ucne.dragonball_planets.domain.character.usecase

import edu.ucne.dragonball_planets.domain.character.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 58,
        name: String? = null,
        gender: String? = null,
        race: String? = null,
    ) = repository.getCharacters(page, limit, name, gender, race)
}