package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Weakness {
    @SerializedName("pokemonType")
    @Expose
    var pokemonType: String? = null
    @SerializedName("effect")
    @Expose
    var effect: Double? = null

}