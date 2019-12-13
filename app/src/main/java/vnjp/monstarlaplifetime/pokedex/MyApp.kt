package vnjp.monstarlaplifetime.pokedex

import android.app.Application
import vnjp.monstarlaplifetime.pokedex.utils.CommonF.CommonF


class MyApp : Application() {

    companion object {
        val pokemonTypeMapping: HashMap<String, Int> = HashMap()
        val pokemonTypesMapping: HashMap<String, Int> = HashMap()
        val pokemonTypesMappingString: HashMap<String, String> = HashMap()
    }


    override fun onCreate() {
        super.onCreate()
        CommonF(this)
        initPokemonTypeMapping()
        initPokemonType()
        initPokemonTypeString()
    }

    private fun initPokemonTypeMapping() {
        pokemonTypeMapping.set("GRASS", R.drawable.ic_types_grass)
        pokemonTypeMapping.set("POISON", R.drawable.ic_types_poison)
        pokemonTypeMapping.set("FIRE", R.drawable.ic_types_fire)
        pokemonTypeMapping.set("FLYING", R.drawable.ic_types_flying)
        pokemonTypeMapping.set("WATER", R.drawable.ic_types_water)
        pokemonTypeMapping.set("BUG", R.drawable.ic_types_bug)
        pokemonTypeMapping.set("DARK", R.drawable.ic_types_dark)
        pokemonTypeMapping.set("DRAGON", R.drawable.ic_types_dragon)
        pokemonTypeMapping.set("ELECTRIC", R.drawable.ic_types_electric)
        pokemonTypeMapping.set("FAIRY", R.drawable.ic_types_fairy)
        pokemonTypeMapping.set("FIGHTING", R.drawable.ic_types_fight)
        pokemonTypeMapping.set("GHOST", R.drawable.ic_types_ghost)
        pokemonTypeMapping.set("GROUND", R.drawable.ic_types_ground)
        pokemonTypeMapping.set("ICE", R.drawable.ic_types_ice)
        pokemonTypeMapping.set("NORMAL", R.drawable.ic_types_normal)
        pokemonTypeMapping.set("PSYCHIC", R.drawable.ic_types_psychic)
        pokemonTypeMapping.set("ROCK", R.drawable.ic_types_rock)
        pokemonTypeMapping.set("STEEL", R.drawable.ic_types_steel)
    }

    private fun initPokemonType() {
        pokemonTypesMapping.set("GRASS", R.drawable.tag_grass)
        pokemonTypesMapping.set("POISON", R.drawable.tag_poison)
        pokemonTypesMapping.set("FIRE", R.drawable.tag_fire)
        pokemonTypesMapping.set("FLYING", R.drawable.tag_flying)
        pokemonTypesMapping.set("WATER", R.drawable.water)
        pokemonTypesMapping.set("BUG", R.drawable.tag_bug)
        pokemonTypesMapping.set("DARK", R.drawable.tag_dark)
        pokemonTypesMapping.set("DRAGON", R.drawable.tag_dragon)
        pokemonTypesMapping.set("ELECTRIC", R.drawable.tag_electric)
        pokemonTypesMapping.set("FAIRY", R.drawable.tag_fairy)
        pokemonTypesMapping.set("FIGHTING", R.drawable.tag_fight)
        pokemonTypesMapping.set("GHOST", R.drawable.tag_ghost)
        pokemonTypesMapping.set("GROUND", R.drawable.tag_ground)
        pokemonTypesMapping.set("ICE", R.drawable.tag_ice)
        pokemonTypesMapping.set("NORMAL", R.drawable.tag_normal)
        pokemonTypesMapping.set("PSYCHIC", R.drawable.tag_psychic)
        pokemonTypesMapping.set("ROCK", R.drawable.tag_rock)
        pokemonTypesMapping.set("STEEL", R.drawable.tag_steel)
    }

    private fun initPokemonTypeString() {
        pokemonTypesMappingString.set("GRASS", "GRASS")
        pokemonTypesMappingString.set("POISON", "POISON")
        pokemonTypesMappingString.set("FIRE", "FIRE")
        pokemonTypesMappingString.set("FLYING", "FLYING")
        pokemonTypesMappingString.set("WATER", "WATER")
        pokemonTypesMappingString.set("BUG", "BUG")
        pokemonTypesMappingString.set("DARK", "DARK")
        pokemonTypesMappingString.set("DRAGON", "DRAGON")
        pokemonTypesMappingString.set("ELECTRIC", "ELECTRIC")
        pokemonTypesMappingString.set("FAIRY", "FAIRY")
        pokemonTypesMappingString.set("FIGHTING", "FIGHTING")
        pokemonTypesMappingString.set("GHOST", "GHOST")
        pokemonTypesMappingString.set("GROUND", "GROUND")
        pokemonTypesMappingString.set("ICE", "ICE")
        pokemonTypesMappingString.set("NORMAL", "NORMAL")
        pokemonTypesMappingString.set("PSYCHIC", "PSYCHIC")
        pokemonTypesMappingString.set("ROCK", "ROCK")
        pokemonTypesMappingString.set("STEEL", "STEEL")
    }
}