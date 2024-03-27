package com.smq.kosmosappsalvador.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme

import com.smq.kosmosappsalvador.characters.data.network.CharacterAux

@Composable
fun CharacterScreen(characterViewModel: CharactersViewModel = viewModel()) {
    val characters by characterViewModel.characters.collectAsState()

    // Lógica para manejar el clic en un personaje
    val onCharacterClick: (CharacterAux) -> Unit = { character ->
        // Implementa aquí la lógica para manejar el clic en un personaje si es necesario
    }

    // Lógica para manejar el clic en el botón de detalles
    val onDetailClick: (CharacterAux) -> Unit = { character ->
        characterViewModel.toggleShowDetail(character)
    }

    Column {
        CharacterList(
            characters = characters,
            onCharacterClick = onCharacterClick,
            onDetailClick = onDetailClick
        )
    }
}


@Composable
fun CharacterList(characters: List<CharacterAux>, onCharacterClick: (CharacterAux) -> Unit, onDetailClick: (CharacterAux) -> Unit) {
    LazyColumn {
        items(characters) { character ->
            CharacterItem(
                character = character,
                onItemClick = { onCharacterClick(character) },
                onDetailClick = { onDetailClick(character) }
            )
        }
    }
}

@Composable
fun CharacterItem(character: CharacterAux, onItemClick: () -> Unit, onDetailClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onItemClick)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Cargar la imagen del personaje con Coil
                val imagePainter = rememberImagePainter(character.image)
                Image(
                    painter = imagePainter,
                    contentDescription = "Character Image",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Mostrar el nombre del personaje
                character.name?.let { Text(text = it, style = MaterialTheme.typography.h5) }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Botón para ver/ocultar el detalle
            Button(
                onClick = onDetailClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = if (character.showDetail) "Ocultar detalle" else "Ver detalle")
            }
            // Mostrar los detalles del personaje si están visibles
            if (character.showDetail) {
                CharacterDetail(character = character)
            }
        }
    }
}


@Composable
fun CharacterDetail(character: CharacterAux) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Status: ${character.status}")
        Text(text = "Species: ${character.species}")
        Text(text = "Type: ${character.type}")
        Text(text = "Gender: ${character.gender}")
        Text(text = "Origin: ${character.origin?.name}")
        Text(text = "Location: ${character.location?.name}")
    }
}