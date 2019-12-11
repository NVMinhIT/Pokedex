package vnjp.monstarlaplifetime.pokedex.screen.detail.movie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon.DetailPokemonActivity

class MovesFragment : Fragment() {
    private lateinit var movieAdapter: MovesAdapter

    private val mReceiver2: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent != null) {
                if (intent.action != null) {
                    if (intent.action == DetailPokemonActivity.ACTION_LIST_MOVES) {
                        try {

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    fun newInstance(): MovesFragment {
        return MovesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            if (activity is DetailPokemonActivity) {
                (activity as DetailPokemonActivity).viewModel.pokemonDetail.observe(this, Observer {
                    Log.d("MINH", it.toString())
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

//        val list: List<Moves> = listOf(
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", ""),
//            ItemMovieType("Pokemon", "level12", "")
//
//
//        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListTypeMovie)
        movieAdapter = MovesAdapter(this.requireContext())
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = movieAdapter
        //movieAdapter.setListItemMovieType(list)

    }
}