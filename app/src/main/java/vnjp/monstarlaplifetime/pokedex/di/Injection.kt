package com.emedinaa.kotlinmvvm.di

import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepository
import vnjp.monstarlaplifetime.pokedex.data.respository.PokemonRepositoryImpl

object Injection {
    //MuseumRepository could be a singleton
    fun providerRepository(): PokemonRepository {
        return PokemonRepositoryImpl()
    }
}