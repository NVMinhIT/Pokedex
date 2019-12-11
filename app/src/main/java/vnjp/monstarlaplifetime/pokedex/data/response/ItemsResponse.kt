package vnjp.monstarlaplifetime.pokedex.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vnjp.monstarlaplifetime.pokedex.data.models.Items


class ItemsResponse {
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<Items>? = null

}