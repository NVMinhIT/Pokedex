package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vnjp.monstarlaplifetime.pokedex.R

class ListPokemonFragment : Fragment() {
    companion object {

        fun newInstance(): ListPokemonFragment {
            return ListPokemonFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_pokemon, container, false)
    }

}