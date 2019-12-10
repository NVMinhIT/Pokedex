package vnjp.monstarlaplifetime.pokedex.screen.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class ListMoviePokemonFactory(private val repository: PokemonRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListMoviePokemonViewModel(repository) as T
    }


}