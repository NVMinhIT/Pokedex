package vnjp.monstarlaplifetime.pokedex.screen.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.models.Move
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class ListMoviePokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    companion object {
        const val TAG = "TAG"
    }

    private val _movie = MutableLiveData<List<Move>>().apply {

        value = emptyList()
    }
    val movies: LiveData<List<Move>> = _movie

    fun loadMovie() {
        repository.getAllMoviePokemon(object : OperationCallback {
            override fun onError(obj: Any?) {
                Log.d(TAG, "No response")
            }

            override fun onSuccess(obj: Any?) {
                _movie.value = obj as List<Move>?
            }
        })
    }
}