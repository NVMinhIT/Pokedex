package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Stats {
    @SerializedName("basicStatses")
    @Expose
    var basicStatses: List<BasicStatse>? = null
    @SerializedName("weaknesses")
    @Expose
    var weaknesses: List<Weakness>? = null
    @SerializedName("breeding")
    @Expose
    var breeding: Breeding? = null

}