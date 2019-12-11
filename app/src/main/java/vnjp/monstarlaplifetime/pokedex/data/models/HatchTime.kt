package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class HatchTime {
    @SerializedName("steps")
    @Expose
    var steps: Int? = null
    @SerializedName("cycles")
    @Expose
    var cycles: Int? = null

}