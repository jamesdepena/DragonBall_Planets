package edu.ucne.dragonball_planets.domain.planet.repository

import edu.ucne.dragonball_planets.data.remote.Resource
import edu.ucne.dragonball_planets.domain.planet.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {

    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}