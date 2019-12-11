package vnjp.monstarlaplifetime.pokedex.data.models

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vnjp.monstarlaplifetime.pokedex.R

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
){
    var pokemonTypeMapping: HashMap<String, Int> =  HashMap()

//    fun initPokemonType() {
//        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_poison)
//        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_poison)
//        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_poison)
//        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_poison)
//        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_poison)
//        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_poison)
//    }
//
//    fun convertData(ls: List<String>, context: Context) {
//        for( i in ls){
//            pokemonTypeMapping.get(i)?.let {
//                var icon: Drawable? = ContextCompat.getDrawable(context, it)
//            }
//
//        }
//    }
}


