package com.smq.kosmosappsalvador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smq.kosmosappsalvador.characters.CharacterScreen
import com.smq.kosmosappsalvador.characters.CharactersViewModel
import com.smq.kosmosappsalvador.ui.theme.KosmosAppSalvadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KosmosAppSalvadorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Inicializa el ViewModel
                    val characterViewModel: CharactersViewModel = viewModel()

                    // Llama a la función getCharacters() para obtener los datos de los personajes
                    LaunchedEffect(true) {
                        characterViewModel.getCharacters()
                    }

                    // Muestra la pantalla principal de la aplicación
                    CharacterScreen(characterViewModel = characterViewModel)
                }
            }
        }
    }
}
