package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

@Suppress("UNCHECKED_CAST")
class ListPokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemon = MutableLiveData<List<Pokemon>>().apply {

        value = emptyList()
    }
    val pokemons: LiveData<List<Pokemon>> = _pokemon

    companion object {
        const val TAG = "TAG"
    }

    private val _pokemonLoadMore = MutableLiveData<List<Pokemon>>()
    val pokemonloadMore: LiveData<List<Pokemon>> = _pokemonLoadMore
    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList
    // lấy dữ liệu từ server
    fun loadPokemon(page: Int) {
        _isViewLoading.postValue(true)
        repository.getAllPokemons(object : OperationCallback {
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
                        _pokemon.value = obj as List<Pokemon>?
                    }
                }

            }
        })
    }

    fun loadPokemonLoadMore(page: Int) {
        _isViewLoading.postValue(true)
        repository.getAllPokemonLoadMore(page, object : OperationCallback {
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
                        _pokemonLoadMore.value = obj as List<Pokemon>?
                    }
                }

            }
        })

    }
}