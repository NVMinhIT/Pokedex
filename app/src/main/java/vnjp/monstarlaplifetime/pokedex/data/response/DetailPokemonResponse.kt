package vnjp.monstarlaplifetime.pokedex.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vnjp.monstarlaplifetime.pokedex.data.models.Evolution
import vnjp.monstarlaplifetime.pokedex.data.models.Moves
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.data.models.Stats


data class DetailPokemonResponse(
    @SerializedName("pokemon")
    @Expose
    var pokemon: Pokemon? = null,
    @SerializedName("stats")
    @Expose
    var stats: Stats? = null,
    @SerializedName("evolutions")
    @Expose
    var evolutions: List<Evolution>? = null,
    @SerializedName("moves")
    @Expose
    var moves: List<Moves>? = null
) {

}