package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Items(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("price")
    @Expose
    var price: Int? = null,
    @SerializedName("image")
    @Expose
    var image: String? = null,
    @SerializedName("effects")
    @Expose
    var effects: String? = null
) {


}