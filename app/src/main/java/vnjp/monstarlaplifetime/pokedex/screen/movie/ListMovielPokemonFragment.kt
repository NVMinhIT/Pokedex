package vnjp.monstarlaplifetime.pokedex.screen.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.kotlinmvvm.di.Injection
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.movie.detail.DetailMoviePokemonActivity

class ListMovielPokemonFragment : Fragment() {
    private lateinit var listSkillAdapter: ListMovieAdapter
    private lateinit var viewmodel: ListMoviePokemonViewModel
    fun newInstance(): ListMovielPokemonFragment {
        return ListMovielPokemonFragment()
    }

    companion object {
        const val BUNDLE_NAME_MOVIE = "BUNDLE_SKILL_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_skill, container, false)
        initView(view)
        viewmodel =
            ViewModelProviders.of(this, ListMoviePokemonFactory(Injection.providerRepository()))
                .get(ListMoviePokemonViewModel::class.java)
        initViewModel()
        return view.rootView
    }

    private fun initViewModel() {
        viewmodel.loadMovie()
        viewmodel.movies.observe(this, Observer {
            listSkillAdapter.setListSkill(it)
        })

    }

    private fun initView(view: View?) {

        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvListSkill)
        listSkillAdapter = ListMovieAdapter(this.requireContext()) {
            val intent = Intent(context, DetailMoviePokemonActivity::class.java)
            intent.putExtra(BUNDLE_NAME_MOVIE, listSkillAdapter.getPositionSkill(it).name)
            startActivity(intent)
        }
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = listSkillAdapter


    }
}