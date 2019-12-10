package vnjp.monstarlaplifetime.pokedex.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import vnjp.monstarlaplifetime.pokedex.data.response.MoviePokemonResponse
import vnjp.monstarlaplifetime.pokedex.data.response.PokemonResponse


interface ApiService {
    @Headers("Accept: application/json")
    @GET("pokemons")
    fun getAllPokemon(): Call<PokemonResponse>

    @Headers("Accept: application/json")
    @GET("moves")
    fun getAllMoviePokemon(): Call<MoviePokemonResponse>
}