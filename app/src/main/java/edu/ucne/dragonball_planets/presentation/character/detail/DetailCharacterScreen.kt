package edu.ucne.dragonball_planets.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.dragonball_planets.domain.character.model.Character

@Composable
fun DetailCharacterScreen(
    viewModel: DetailCharacterViewModel = hiltViewModel(),
    onDrawer: () -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailCharacterBodyScreen(
        state = state,
        onDrawer = onDrawer,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCharacterBodyScreen(
    state: DetailCharacterUiState,
    onDrawer: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Personaje") },
                navigationIcon = {
                    IconButton(onClick = onDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                },
                actions = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.character?.let { character ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Raza: ${character.race}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Género: ${character.gender}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Ki: ${character.ki}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Máx Ki: ${character.maxKi}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Descripción:",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = character.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailCharacterBodyScreenPreview() {
    val state = DetailCharacterUiState(
        character = Character(
            id = 1,
            name = "Goku",
            ki = "60.000.000",
            race = "Saiyan",
            gender = "Male",
            description = "El protagonista de la serie.",
            image = "",
            maxKi = "90.000.000.000"
        )
    )
    MaterialTheme {
        Surface {
            DetailCharacterBodyScreen(state = state, onDrawer = {}, onBack = {})
        }
    }
}