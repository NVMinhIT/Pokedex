package vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class DetailsPokemonFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsPokemonViewModel(repository) as T
    }
}