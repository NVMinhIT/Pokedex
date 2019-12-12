package vnjp.monstarlaplifetime.pokedex.screen.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository

class WeakNessesFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeakNessesViewModel(repository) as T
    }
}