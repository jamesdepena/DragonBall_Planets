package edu.ucne.dragonball_planets.data.repository

import edu.ucne.dragonball_planets.data.remote.Resource
import edu.ucne.dragonball_planets.data.remote.remotedatasource.PlanetRemoteDataSource
import edu.ucne.dragonball_planets.domain.planet.model.Planet
import edu.ucne.dragonball_planets.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>> = flow {

        emit(Resource.Loading())

        val response = remoteDataSource.getPlanets(page, limit, name)
        response.onSuccess { planets ->
            emit(Resource.Success(planets.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planet>> = flow {

        emit(Resource.Loading())

        val response = remoteDataSource.getPlanetDetail(id)
        response.onSuccess { planet ->
            emit(Resource.Success(planet.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }
}