package edu.ucne.dragonball_planets.domain.planet.usecase

import edu.ucne.dragonball_planets.domain.planet.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int) = repository.getPlanetDetail(id)
}