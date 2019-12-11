package vnjp.monstarlaplifetime.pokedex.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Breeding {
    @SerializedName("eggGroup")
    @Expose
    var eggGroup: List<String>? = null
    @SerializedName("hatchTime")
    @Expose
    var hatchTime: HatchTime? = null
    @SerializedName("femaleGender")
    @Expose
    var femaleGender: Double? = null

}