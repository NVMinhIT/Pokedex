package vnjp.monstarlaplifetime.pokedex

import android.app.Application

class MyApp : Application() {

    companion object {
        val pokemonTypeMapping: HashMap<String, Int> = HashMap()
    }


    override fun onCreate() {
        super.onCreate()
        initPokemonTypeMapping()
    }

    private fun initPokemonTypeMapping() {
        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_grass)
        pokemonTypeMapping.set("POISON", R.drawable.ic_types_poison)
        pokemonTypeMapping.set("FIRE", R.drawable.ic_types_fire)
        pokemonTypeMapping.set("FLYING", R.drawable.ic_types_flying)
        pokemonTypeMapping.set("WATER", R.drawable.ic_water)
    }
}