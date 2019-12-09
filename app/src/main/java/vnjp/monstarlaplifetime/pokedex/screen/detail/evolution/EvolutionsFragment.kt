package vnjp.monstarlaplifetime.pokedex.screen.detail.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vnjp.monstarlaplifetime.pokedex.R

class EvolutionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_evolutions, container, false)
        return view.rootView
    }
}