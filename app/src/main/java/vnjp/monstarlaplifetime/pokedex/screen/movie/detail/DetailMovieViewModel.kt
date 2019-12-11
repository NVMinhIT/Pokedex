package vnjp.monstarlaplifetime.pokedex.screen.movie.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.models.Move
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class DetailMovieViewModel(val repository: PokemonRepository) : ViewModel() {
    companion object {
        const val TAG = "CONSOLE"
    }

    private val _moviePokemon = MutableLiveData<Move>()

    val moviesPokemon: LiveData<Move> = _moviePokemon

    fun loadPokemonByName(name: String) {
        repository.getMoviePokemon(name, object : OperationCallback {
            override fun onSuccess(obj: Any?) {
                _moviePokemon.value = obj as Move?
            }

            override fun onError(obj: Any?) {
                Log.d(TAG, "No response")
            }
        })
    }
}