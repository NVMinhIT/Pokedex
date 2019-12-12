package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import vnjp.monstarlaplifetime.pokedex.R

class PokemonPropertiesMapping(var drawableID: Int? = 0, var colorID: Int? = 0)
    fun initHashMap(){

        val myHasMap: HashMap<String, PokemonPropertiesMapping> = HashMap()

        val po1 = PokemonPropertiesMapping(R.drawable.ic_types_flying, R.color.dark_sky_blue)
        myHasMap.set("FLY", po1)
    }
