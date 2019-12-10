package vnjp.monstarlaplifetime.pokedex.data.response

import com.google.gson.annotations.SerializedName

enum class PokemonTypes {
    @SerializedName("GRASS")
    GRASS,
    @SerializedName("POISON")
    POISON,
    @SerializedName("FIRE")
    FIRE,
    @SerializedName("FLYING")
    FLYING,
    @SerializedName("WATER")
    WATER,
}