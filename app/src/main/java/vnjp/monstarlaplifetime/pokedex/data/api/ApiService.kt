package vnjp.monstarlaplifetime.pokedex.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import vnjp.monstarlaplifetime.pokedex.data.models.Items
import vnjp.monstarlaplifetime.pokedex.data.models.Move
import vnjp.monstarlaplifetime.pokedex.data.models.Weakness
import vnjp.monstarlaplifetime.pokedex.data.response.DetailPokemonResponse
import vnjp.monstarlaplifetime.pokedex.data.response.ItemsResponse
import vnjp.monstarlaplifetime.pokedex.data.response.MoviePokemonResponse
import vnjp.monstarlaplifetime.pokedex.data.response.PokemonResponse


interface ApiService {
    @Headers("Accept: application/json")
    @GET("pokemons")
    fun getAllPokemon(): Call<PokemonResponse>

    @Headers("Accept: application/json")
    @GET("pokemons")
    fun getAllPokemonLoadMore(@Query("page") page: Int): Call<PokemonResponse>

    @Headers("Accept: application/json")
    @GET("moves")
    fun getAllMoviePokemon(@Query("page") page: Int): Call<MoviePokemonResponse>

    @Headers("Accept: application/json")
    @GET("moves/{name}")
    fun getMoviePokemon(@Path("name") namePokemon: String): Call<Move>

    @Headers("Accept: application/json")
    @GET("items")
    fun getAllItems(@Query("page") page: Int): Call<ItemsResponse>

    @Headers("Accept: application/json")
    @GET("items/{name}")
    fun getItemsPokemonByName(@Path("name") nameItems: String): Call<Items>

    @Headers("Accept: application/json")
    @GET("pokemons/{id}")
    fun getDetailPokemonById(@Path("id") nameItems: String): Call<DetailPokemonResponse>


    @Headers("Accept: application/json")
    @GET("weaknesses/{type}")
    fun getWeakNesses(@Path("type") type: String): Call<List<Weakness>>
}