package vnjp.monstarlaplifetime.pokedex.data.respository


import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vnjp.monstarlaplifetime.pokedex.data.api.ApiService
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.api.ServiceRetrofit
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.data.response.MoviePokemonResponse
import vnjp.monstarlaplifetime.pokedex.data.response.PokemonResponse

class PokemonRepositoryImpl : PokemonRepository {
    companion object {

        const val TAG = "CONSOLE"
    }

    private var listpokemon: List<Pokemon> = emptyList()
    fun getPokemonList(): List<Pokemon?>? {
        return listpokemon
    }


    override fun getAllPokemons(callback: OperationCallback) {
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getAllPokemon().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.v(TAG, "data ${it.pokemons}")
                        callback.onSuccess(it.pokemons)
                    } else {
                        callback.onError(it.total)
                    }
                }


            }

            override fun onFailure(
                call: Call<PokemonResponse>,
                t: Throwable
            ) {
                callback.onError(t.message)
            }
        })


    }

    override fun getAllMoviePokemon(callback: OperationCallback) {
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getAllMoviePokemon().enqueue(object : Callback<MoviePokemonResponse> {
            override fun onResponse(
                call: Call<MoviePokemonResponse>,
                response: Response<MoviePokemonResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it.moves)
                    } else {
                        callback.onError("No Response")
                    }
                }


            }

            override fun onFailure(
                call: Call<MoviePokemonResponse>,
                t: Throwable
            ) {
                callback.onError(t.message)
            }
        })

    }

    override fun cancel() {
        Log.d(TAG, "No Response")

    }


}