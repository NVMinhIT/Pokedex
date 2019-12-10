package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Move {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("effects")
    @Expose
    var effects: String? = null
    @SerializedName("power")
    @Expose
    var power: Int? = null
    @SerializedName("accuracy")
    @Expose
    var accuracy: Int? = null
    @SerializedName("pp")
    @Expose
    var pp: Int? = null

}