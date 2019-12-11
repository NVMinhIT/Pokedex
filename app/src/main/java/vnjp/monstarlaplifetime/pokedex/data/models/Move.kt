package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("effects")
    @Expose
    var effects: String,
    @SerializedName("power")
    @Expose
    var power: Int,
    @SerializedName("accuracy")
    @Expose
    var accuracy: Int,
    @SerializedName("pp")
    @Expose
    var pp: Int
)


