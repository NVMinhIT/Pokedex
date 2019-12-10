package vnjp.monstarlaplifetime.pokedex.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon


class PokemonResponse {
    @SerializedName("pokemons")
    @Expose
    var pokemons: List<Pokemon>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

}
