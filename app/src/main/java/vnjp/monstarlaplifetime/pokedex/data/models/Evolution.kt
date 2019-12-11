package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Evolution {
    @SerializedName("pokemonName")
    @Expose
    var pokemonName: String? = null
    @SerializedName("pokemonImage")
    @Expose
    var pokemonImage: String? = null
    @SerializedName("evolutionPokemonName")
    @Expose
    var evolutionPokemonName: String? = null
    @SerializedName("evolutionpokemonImage")
    @Expose
    var evolutionpokemonImage: String? = null
    @SerializedName("evolutionLevel")
    @Expose
    var evolutionLevel: Int? = null

}