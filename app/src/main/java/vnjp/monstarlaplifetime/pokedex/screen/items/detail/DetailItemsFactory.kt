package vnjp.monstarlaplifetime.pokedex.screen.items.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class DetailItemsFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailItemsViewModel(repository) as T
    }
}