package vnjp.monstarlaplifetime.pokedex.screen.detail.start

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_start.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.BasicStatse
import vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon.DetailPokemonActivity
import vnjp.monstarlaplifetime.pokedex.screen.dialog.ListWeakNessesAdapter


class StartFragment : Fragment() {
    private lateinit var listWeakNessesAdapter: ListWeakNessesAdapter
    private var list: List<BasicStatse> = emptyList()
    private var basicStatse: List<BasicStatse> = emptyList()
    fun newInstance(): StartFragment {
        return StartFragment()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            if (activity is DetailPokemonActivity) {
                (activity as DetailPokemonActivity).viewModel.pokemonDetail.observe(this, Observer {
                    it.stats?.weaknesses?.let { it1 -> listWeakNessesAdapter.setList(it1) }

                    Log.d("MINH", it.stats?.basicStatses.toString())
                    basicStatse = it.stats?.basicStatses!!
                })
                progress_bar1.max = basicStatse.get(0).maxValue!!
                progress_bar1.min = basicStatse.get(0).value!!
                progress_bar2.max = basicStatse.get(1).maxValue!!
                progress_bar2.min = basicStatse.get(1).value!!
                progress_bar3.max = basicStatse.get(2).maxValue!!
                progress_bar3.min = basicStatse.get(2).value!!
                progress_bar4.max = basicStatse.get(3).maxValue!!
                progress_bar4.min = basicStatse.get(3).value!!
                progress_bar5.max = basicStatse.get(4).maxValue!!
                progress_bar5.min = basicStatse.get(4).value!!
                progress_bar6.max = basicStatse.get(5).maxValue!!
                progress_bar6.min = basicStatse.get(5).value!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        initView(view)
        return view.rootView
    }


    private fun initView(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvWeakNesses)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = GridLayoutManager(context, 3)
        listWeakNessesAdapter = ListWeakNessesAdapter(this.requireContext())
        recyclerView?.adapter = listWeakNessesAdapter


    }


}