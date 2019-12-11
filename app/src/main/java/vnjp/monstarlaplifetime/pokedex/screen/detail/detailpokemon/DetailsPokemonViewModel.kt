package vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.response.DetailPokemonResponse
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class DetailsPokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<DetailPokemonResponse>()


    val pokemonDetail: LiveData<DetailPokemonResponse> = _pokemonDetail

    companion object {
        const val TAG = "TAG"
    }

    fun loadDetailPokemon(id: String) {
        repository.getDetailPokemonById(id, object : OperationCallback {
            override fun onSuccess(obj: Any?) {
                _pokemonDetail.value = obj as DetailPokemonResponse?
            }

            override fun onError(obj: Any?) {
                Log.d(TAG, "No Response")
            }
        })
    }

}