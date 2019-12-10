package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

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
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.screen.detail.DetailPokemonActivity
import vnjp.monstarlaplifetime.pokedex.screen.dialog.WeakNessPokemonDialogFragment

class ListPokemonFragment : Fragment(), ListPokemonAdapter.ILongClickItemCategoryListener {

    private lateinit var listPokemonAdapter: ListPokemonAdapter
    private lateinit var viewModel: ListPokemonViewModel
    var arr = arrayListOf<Pokemon>()

    companion object {
        const val BUNDLE_STUDENT_ID = "BUNDLE_STUDENT_ID"
    }

    fun newInstance(): ListPokemonFragment {
        return ListPokemonFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_pokemon, container, false)
        initView(view)
        viewModel = ViewModelProviders.of(this, ListPokemonFactory(Injection.providerRepository()))
            .get(ListPokemonViewModel::class.java)
        initEvent()
        initViewModel()
        return view.rootView
    }

    private fun initViewModel() {
        viewModel.loadPokemon()
        viewModel.pokemons.observe(this, Observer {
            listPokemonAdapter.setList(it)
        })


    }

    private fun initEvent() {

    }

    private fun initView(view: View) {


        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListPokemon)
        listPokemonAdapter = ListPokemonAdapter(this.requireActivity()) {
            val intent = Intent(context, DetailPokemonActivity::class.java)
            intent.putExtra(BUNDLE_STUDENT_ID, listPokemonAdapter.getPosition(it).id)
            startActivity(intent)

        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listPokemonAdapter
        listPokemonAdapter.setLongClickItemCategoryListener(this)


    }

    override fun onLongClickItemCategory(pokemon: Pokemon?) {
        val fragment =
            WeakNessPokemonDialogFragment()
        fragment.show(childFragmentManager, fragment.javaClass.simpleName)
    }

}