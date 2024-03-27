package com.smq.kosmosappsalvador.characters

import androidx.lifecycle.ViewModel
import com.smq.kosmosappsalvador.characters.data.network.CharacterAux
import com.smq.kosmosappsalvador.util.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class CharactersViewModel : ViewModel() {
    private var _characters = MutableStateFlow<List<CharacterAux>>(emptyList())
    var characters: StateFlow<List<CharacterAux>> = _characters

    private val apiService = RetrofitHelper.apiService

    suspend fun getCharacters() {
        return withContext(Dispatchers.IO) {
            val charactersResponse = apiService.getCharacters()
            val charactersList = charactersResponse.results ?: emptyList() // Manejar el posible valor nulo
            _characters.value = charactersList.filterNotNull() // Filtrar los elementos nulos
        }
    }

    fun toggleShowDetail(character: CharacterAux) {
        character.showDetail = !character.showDetail
    }

}