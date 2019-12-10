package vnjp.monstarlaplifetime.pokedex.data.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

import vnjp.monstarlaplifetime.pokedex.data.models.Move


class MoviePokemonResponse {
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("moves")
    @Expose
    var moves: List<Move?>? = null


}