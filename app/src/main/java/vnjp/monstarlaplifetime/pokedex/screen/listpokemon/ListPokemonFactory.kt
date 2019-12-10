package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class ListPokemonFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListPokemonViewModel(repository) as T
    }
}