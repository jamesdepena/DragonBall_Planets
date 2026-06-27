package edu.ucne.dragonball_planets.domain.character.repository

import edu.ucne.dragonball_planets.data.remote.Resource
import edu.ucne.dragonball_planets.domain.character.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Flow<Resource<List<Character>>>

    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}