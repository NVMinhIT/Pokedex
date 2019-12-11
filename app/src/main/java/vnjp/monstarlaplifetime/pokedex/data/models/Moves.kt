package vnjp.monstarlaplifetime.pokedex.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Moves(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("level")
    @Expose
    var level: Int? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null
) : Parcelable {


    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        level = parcel.readValue(Int::class.java.classLoader) as? Int
        type = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(level)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Moves> {
        override fun createFromParcel(parcel: Parcel): Moves {
            return Moves(parcel)
        }

        override fun newArray(size: Int): Array<Moves?> {
            return arrayOfNulls(size)
        }
    }

}