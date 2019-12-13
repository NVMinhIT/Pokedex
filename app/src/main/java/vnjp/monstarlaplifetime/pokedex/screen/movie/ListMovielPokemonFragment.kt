package vnjp.monstarlaplifetime.pokedex.screen.movie

import android.content.Context
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
import kotlinx.android.synthetic.main.fragment_list_pokemon.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.main.MainActivity
import vnjp.monstarlaplifetime.pokedex.screen.movie.detail.DetailMoviePokemonActivity

class ListMovielPokemonFragment : Fragment(), MainActivity.MyInterfacePokemon {
    private lateinit var listSkillAdapter: ListMovieAdapter
    private lateinit var viewmodel: ListMoviePokemonViewModel
    fun newInstance(): ListMovielPokemonFragment {
        return ListMovielPokemonFragment()
    }

    companion object {
        const val BUNDLE_NAME_MOVIE = "BUNDLE_SKILL_ID"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity.let {
            if (activity is MainActivity) {
                (activity as MainActivity).setMyinterFace(this)
            }

        }
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
        viewmodel.isViewLoading.observe(this, Observer {
            val visibility = if (it) View.VISIBLE else View.GONE
            progressBar.visibility = visibility
        })
        viewmodel.onMessageError.observe(this, Observer {
            layoutError.visibility = View.VISIBLE
            layoutEmpty.visibility = View.GONE
            // textViewError.text= "Error $it"
        })
        viewmodel.isEmptyList.observe(this, Observer {
            layoutEmpty.visibility = View.VISIBLE
            layoutError.visibility = View.GONE
        })
    }

    private fun initView(view: View?) {

        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvListSkill)
        listSkillAdapter = ListMovieAdapter(this.requireContext()) {
            val intent = Intent(context, DetailMoviePokemonActivity::class.java)
            intent.putExtra(BUNDLE_NAME_MOVIE, listSkillAdapter.getPositionSkill(it).name)
            startActivity(intent)
        }
        recyclerView?.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        recyclerView?.adapter = listSkillAdapter


    }

    override fun buttonClicked(name: String) {
        listSkillAdapter.filterMoves(name)
    }

    override fun loadPokemon() {
        initViewModel()
    }
}