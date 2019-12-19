package vnjp.monstarlaplifetime.pokedex.data.respository

import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback

interface PokemonRepository {
    fun getAllPokemons(callback: OperationCallback)
    fun getAllMoviePokemon(callback: OperationCallback)
    fun getMoviePokemon(name: String, callback: OperationCallback)
    fun getAllItemsPokemon(callback: OperationCallback)
    fun getItemsPokemonByName(name: String, callback: OperationCallback)
    fun getDetailPokemonById(id: String, callback: OperationCallback)
    fun getWeakNesses(type: String, callback: OperationCallback)
    fun getAllPokemonLoadMore(Page: Int, records: Int, callback: OperationCallback)


    fun cancel()
}