package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pokemon {
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("pokemonTypes")
    @Expose
    var pokemonTypes: List<String>? = null

}

