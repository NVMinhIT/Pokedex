package vnjp.monstarlaplifetime.pokedex.screen.detail.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.ItemMovieType

class MovieFragment : Fragment() {
    private lateinit var movieAdapter: MovieAdapter
    fun newInstance(): MovieFragment {
        return MovieFragment()
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

        val list: List<ItemMovieType> = listOf(
            ItemMovieType(0, "", "Bubble", "Level 11"),
            ItemMovieType(1, "", "Bubble", "Level 12"),
            ItemMovieType(2, "", "Bubble", "Level4"),
            ItemMovieType(3, "", "Bubble", "Level 12"),
            ItemMovieType(4, "", "Bubble", "Level 8"),
            ItemMovieType(5, "", "Bubble", "Level 12"),
            ItemMovieType(6, "", "Bubble", "Level 8"),
            ItemMovieType(7, "", "Bubble", "Level 12"),
            ItemMovieType(8, "", "Bubble", "Level 10"),
            ItemMovieType(9, "", "Bubble", "Level 7"),
            ItemMovieType(10, "", "Bubble", "Level 12")
        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListTypeMovie)
        movieAdapter = MovieAdapter(this.requireContext())
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = movieAdapter
        movieAdapter.setListItemMovieType(list)

    }
}