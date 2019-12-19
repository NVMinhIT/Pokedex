package vnjp.monstarlaplifetime.pokedex.data.respository


import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.api.ApiService
import vnjp.monstarlaplifetime.pokedex.data.api.OperationCallback
import vnjp.monstarlaplifetime.pokedex.data.api.ServiceRetrofit
import vnjp.monstarlaplifetime.pokedex.data.models.Items
import vnjp.monstarlaplifetime.pokedex.data.models.Move
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.data.models.Weakness
import vnjp.monstarlaplifetime.pokedex.data.response.DetailPokemonResponse
import vnjp.monstarlaplifetime.pokedex.data.response.ItemsResponse
import vnjp.monstarlaplifetime.pokedex.data.response.MoviePokemonResponse
import vnjp.monstarlaplifetime.pokedex.data.response.PokemonResponse
import vnjp.monstarlaplifetime.pokedex.utils.CommonF

class PokemonRepositoryImpl : PokemonRepository {
    companion object {

        const val TAG = "CONSOLE"
    }

    private var listpokemon: List<Pokemon> = emptyList()
    fun getPokemonList(): List<Pokemon?>? {
        return listpokemon
    }

    //lấy danh sách pokemon
    override fun getAllPokemons(callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
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
                        Log.d(TAG, "size ${it.pokemons?.size}")
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

    // lấy danh sách moves pokemon
    override fun getAllMoviePokemon(callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
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

    //lấy detail pokemon theo tên
    override fun getMoviePokemon(name: String, callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getMoviePokemon(name).enqueue(object : Callback<Move> {
            override fun onResponse(call: Call<Move>, response: Response<Move>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(response.body())
                    } else {
                        callback.onError("No Response")
                    }
                }
            }

            override fun onFailure(call: Call<Move>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    // lấy danh sách items pokemon
    override fun getAllItemsPokemon(callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getAllItems().enqueue(object : Callback<ItemsResponse> {
            override fun onResponse(
                call: Call<ItemsResponse>,
                response: Response<ItemsResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it.items)
                    } else {
                        callback.onError("No Response")
                    }
                }

            }

            override fun onFailure(
                call: Call<ItemsResponse>,
                t: Throwable
            ) {
                callback.onError(t.message)
            }
        })

    }

    // lấy items theo tên
    override fun getItemsPokemonByName(name: String, callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getItemsPokemonByName(name).enqueue(object : Callback<Items> {
            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(response.body())
                    } else {
                        callback.onError("No Response")
                    }
                }
            }

            override fun onFailure(call: Call<Items>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    // lấy detail pokemon theo id
    override fun getDetailPokemonById(id: String, callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getDetailPokemonById(id).enqueue(object : Callback<DetailPokemonResponse> {
            override fun onResponse(
                call: Call<DetailPokemonResponse>,
                response: Response<DetailPokemonResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(response.body())
                    } else {
                        callback.onError("No Response")
                    }
                }
            }

            override fun onFailure(call: Call<DetailPokemonResponse>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    // lấy weakness pokemon
    override fun getWeakNesses(type: String, callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getWeakNesses(type).enqueue(object : Callback<List<Weakness>> {
            override fun onResponse(
                call: Call<List<Weakness>>,
                response: Response<List<Weakness>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError("No Response")
                    }
                }


            }

            override fun onFailure(
                call: Call<List<Weakness>>,
                t: Throwable
            ) {
                callback.onError(t.message)
            }
        })
    }

    override fun getAllPokemonLoadMore(Page: Int, records: Int, callback: OperationCallback) {
        if (!CommonF.isNetworkAvailable()) {
            CommonF.showToastError(R.string.noInternet)
            return
        }
        val apiService: ApiService = ServiceRetrofit().getService()
        apiService.getAllPokemonLoadMore(Page, records).enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError("No Response")
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

    override fun cancel() {
        Log.d(TAG, "No Response")

    }


}