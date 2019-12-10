package vnjp.monstarlaplifetime.pokedex.data.respository

import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback

interface PokemonRepository {
    fun getAllPokemons(callback: OperationCallback)
    fun getAllMoviePokemon(callback: OperationCallback)
    fun cancel()
}