package edu.ucne.dragonball_planets.data.remote.remotedatasource

import edu.ucne.dragonball_planets.data.remote.DragonBallApi
import edu.ucne.dragonball_planets.data.remote.dto.PlanetDto
import edu.ucne.dragonball_planets.data.remote.dto.PlanetResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class PlanetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {

    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Result<List<PlanetDto>> {
        return try {
            val list: List<PlanetDto> = if (!name.isNullOrBlank()) {
                val response = api.searchPlanets(name)
                if (!response.isSuccessful)
                    return Result.failure(Exception("Error de red ${response.code()}"))
                response.body() ?: emptyList()
            } else {
                val response = api.getPlanets(page, limit)
                if (!response.isSuccessful)
                    return Result.failure(Exception("Error de red ${response.code()}"))
                response.body()?.items ?: emptyList()
            }
            Result.success(list)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful)
                return Result.failure(Exception("Error de red ${response.code()}"))
            Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}