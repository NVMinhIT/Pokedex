package vnjp.monstarlaplifetime.pokedex.data.respository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon

interface PokemonRepository {
    suspend fun getAllPokemonAsync(): Deferred<List<Pokemon>>

    suspend fun addPokemon(pokemon: Pokemon): Job
}