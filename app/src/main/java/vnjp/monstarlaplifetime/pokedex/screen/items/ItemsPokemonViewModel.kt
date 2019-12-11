package vnjp.monstarlaplifetime.pokedex.screen.items

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.models.Items
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class ItemsPokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    companion object {
        const val TAG = "TAG"
    }

    private val _items = MutableLiveData<List<Items>>().apply {

        value = emptyList()
    }
    val items: LiveData<List<Items>> = _items


    fun loadItems() {
        repository.getAllItemsPokemon(object : OperationCallback {
            override fun onError(obj: Any?) {
                Log.d(TAG, "No response")
            }

            override fun onSuccess(obj: Any?) {
                _items.value = obj as List<Items>?
            }
        })
    }
}