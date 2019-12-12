package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class BasicStatse(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("value")
    @Expose
    var value: Int? = null,
    @SerializedName("maxValue")
    @Expose
    var maxValue: Int? = null
)

