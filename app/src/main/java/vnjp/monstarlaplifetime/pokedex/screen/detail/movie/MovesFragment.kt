package vnjp.monstarlaplifetime.pokedex.screen.detail.movie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Moves
import vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon.DetailPokemonActivity

class MovesFragment : Fragment() {
    private lateinit var movieAdapter: MovesAdapter
    private var listMoves: List<Moves> = emptyList()

    fun newInstance(): MovesFragment {
        return MovesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            if (activity is DetailPokemonActivity) {
                (activity as DetailPokemonActivity).viewModel.pokemonDetail.observe(this, Observer {
                    it.moves?.let {
                            it1 -> movieAdapter.setListItemMovieType(it1) }
                })
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie, container, false)
        initView(view)
        return view.rootView
    }

    private fun initView(view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListTypeMovie)
        movieAdapter = MovesAdapter(this.requireContext())
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = movieAdapter


    }
}