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
    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading
    val movies: LiveData<List<Move>> = _movie
    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadMovie() {
        _isViewLoading.postValue(true)
        repository.getAllMoviePokemon(object : OperationCallback {
            override fun onError(obj: Any?) {
                Log.d(TAG, "No response")
                _isViewLoading.postValue(false)
                _onMessageError.postValue(obj)
            }

            override fun onSuccess(obj: Any?) {
                _isViewLoading.postValue(false)
                if (obj != null && obj is List<*>) {
                    if (obj.isEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        _movie.value = obj as List<Move>?
                    }
                }

            }
        })
    }
}