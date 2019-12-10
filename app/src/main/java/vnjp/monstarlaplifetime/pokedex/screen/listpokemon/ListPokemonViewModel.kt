package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class ListPokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemon = MutableLiveData<List<Pokemon>>().apply {

        value = emptyList()
    }
    val pokemons: LiveData<List<Pokemon>> = _pokemon

    companion object {
        const val TAG = "TAG"
    }

    fun loadPokemon() {
        repository.getAllPokemons(object : OperationCallback {
            override fun onError(obj: Any?) {
                Log.d(TAG, "No response")
            }

            override fun onSuccess(obj: Any?) {
                _pokemon.value = obj as List<Pokemon>?
            }
        })
    }
}