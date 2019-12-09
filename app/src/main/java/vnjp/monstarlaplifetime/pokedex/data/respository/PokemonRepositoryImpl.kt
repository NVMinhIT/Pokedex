package vnjp.monstarlaplifetime.pokedex.data.respository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon

class PokemonRepositoryImpl : PokemonRepository {
    override suspend fun getAllPokemonAsync(): Deferred<List<Pokemon>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addPokemon(pokemon: Pokemon): Job {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}