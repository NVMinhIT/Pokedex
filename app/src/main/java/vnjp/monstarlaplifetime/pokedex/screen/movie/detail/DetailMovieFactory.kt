package vnjp.monstarlaplifetime.pokedex.screen.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class DetailMovieFactory(val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailMovieViewModel(repository) as T
    }
}