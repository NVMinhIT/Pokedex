package vnjp.monstarlaplifetime.pokedex.screen.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import vnjp.monstarlaplifetime.pokedex.data.models.Move
import vnjp.monstarlaplifetime.pokedex.screen.main.MainActivity
import vnjp.monstarlaplifetime.pokedex.screen.movie.detail.DetailMoviePokemonActivity

class ListMovielPokemonFragment : Fragment(), MainActivity.MyInterfacePokemon {
    private lateinit var listSkillAdapter: ListMovieAdapter
    private lateinit var viewmodel: ListMoviePokemonViewModel
    private lateinit var recyclerView: RecyclerView
    private var arr: MutableList<Move?> = arrayListOf()
    private var isLoading: Boolean = false
    fun newInstance(): ListMovielPokemonFragment {
        return ListMovielPokemonFragment()
    }

    companion object {
        const val BUNDLE_NAME_MOVIE = "BUNDLE_SKILL_ID"
        var pageIndex = 1
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
        initScrollListener()
        initViewModel()

        return view.rootView
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == arr.size - 1) { //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })

    }

    private fun loadMore() {
        arr.add(null)
        listSkillAdapter.notifyItemInserted(arr.size - 1)
        //val scrollPosition: Int = arr.size - 1
        listSkillAdapter.notifyItemRemoved(arr.size)
        arr.removeAt(arr.size - 1)
        val handler = Handler()
        handler.postDelayed(Runnable {
            //arr.removeAt(arr.size - 1)
            pageIndex++
            viewmodel.loadMovie(pageIndex)
            listSkillAdapter.notifyDataSetChanged()
            isLoading = false
        }, 2000)
    }

    private fun initViewModel() {
        viewmodel.loadMovie(1)
        viewmodel.movies.observe(this, Observer {
            arr.addAll(it)
            listSkillAdapter.setListSkill(arr)
            Log.d("HAHA", "${arr.size}")

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
//            layoutEmpty.visibility = View.VISIBLE
//            layoutError.visibility = View.GONE
        })
    }

    private fun initView(view: View?) {

        recyclerView = view!!.findViewById(R.id.rvListSkill)
        listSkillAdapter = ListMovieAdapter(this.requireContext()) {
            val intent = Intent(context, DetailMoviePokemonActivity::class.java)
            intent.putExtra(BUNDLE_NAME_MOVIE, listSkillAdapter.getPositionSkill(it).name)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listSkillAdapter


    }

    override fun buttonClicked(name: String) {
        listSkillAdapter.filterMoves(name)
    }

    override fun loadPokemon() {
        initViewModel()
    }
}