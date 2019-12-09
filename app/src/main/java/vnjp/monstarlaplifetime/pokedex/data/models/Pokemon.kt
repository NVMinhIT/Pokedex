package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val pokemonId: String? = null,
    @SerializedName("name")
    val pokemonName: String? = null,
    @SerializedName("image")
    val pokemonImage: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("pokemonTypes")
    val pokemonTypes: String? = null
)