package vnjp.monstarlaplifetime.pokedex.screen.detail.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon.DetailPokemonActivity

class EvolutionsFragment : Fragment() {

    private lateinit var evolutionAdapter: EvolutionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            if (activity is DetailPokemonActivity) {
                (activity as DetailPokemonActivity).viewModel.pokemonDetail.observe(this, Observer {
                    it.evolutions?.let { it1 -> evolutionAdapter.setListEvolution(it1) }
                })
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_evolutions, container, false)
        initView(view)
        return view.rootView
    }

    private fun initView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListItemEvolution)
        recyclerView.layoutManager = LinearLayoutManager(context)
        evolutionAdapter = EvolutionAdapter(this.requireContext())
        recyclerView.adapter = evolutionAdapter
    }
}