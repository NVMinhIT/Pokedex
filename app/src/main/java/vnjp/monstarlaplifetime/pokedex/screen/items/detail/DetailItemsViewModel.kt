package vnjp.monstarlaplifetime.pokedex.screen.items.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.models.Items
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository
import vnjp.monstarlaplifetime.pokedex.screen.movie.detail.DetailMovieViewModel

class DetailItemsViewModel(private val repository: PokemonRepository) : ViewModel() {
    companion object {
        const val TAG = "TAG"
    }

    private val _itemsPokemon = MutableLiveData<Items>()

    val itemsPokemon: LiveData<Items> = _itemsPokemon
    fun loadItemsPokemonName(name: String) {
        repository.getItemsPokemonByName(name, object : OperationCallback {
            override fun onSuccess(obj: Any?) {
                _itemsPokemon.value = obj as Items?
            }

            override fun onError(obj: Any?) {
                Log.d(DetailMovieViewModel.TAG, "No response")
            }
        })

    }
}
