package edu.ucne.dragonball_planets.data.remote.remotedatasource

import edu.ucne.dragonball_planets.data.remote.DragonBallApi
import edu.ucne.dragonball_planets.data.remote.dto.CharacterDto
import edu.ucne.dragonball_planets.data.remote.dto.CharacterResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {

    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Result<List<CharacterDto>> {
        return try {
            val list: List<CharacterDto> = if (!name.isNullOrBlank() || !gender.isNullOrBlank() || !race.isNullOrBlank()) {
                val response = api.searchCharacters(name, gender, race)
                if (!response.isSuccessful) {
                    return Result.failure(Exception("Error de red ${response.code()}"))
                }
                response.body() ?: emptyList()
            } else {
                val response = api.getCharacters(page, limit)
                if (!response.isSuccessful) {
                    return Result.failure(Exception("Error de red ${response.code()}"))
                }
                response.body()?.items ?: emptyList()
            }
            Result.success(list)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getCharacterDetail(id: Int): Result<CharacterDto> {
        return try {
            val response = api.getCharacterDetail(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}