package vnjp.monstarlaplifetime.pokedex.screen.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class ItemsPokemonFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemsPokemonViewModel(repository) as T
    }
}