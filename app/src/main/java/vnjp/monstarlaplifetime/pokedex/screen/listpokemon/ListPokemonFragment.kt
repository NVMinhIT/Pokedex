package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

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
import kotlinx.android.synthetic.main.customtoast_layout.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon.DetailPokemonActivity
import vnjp.monstarlaplifetime.pokedex.screen.dialog.WeakNessPokemonDialogFragment
import vnjp.monstarlaplifetime.pokedex.screen.main.MainActivity


class ListPokemonFragment : Fragment(), ListPokemonAdapter.ILongClickItemCategoryListener,
    MainActivity.MyInterfacePokemon {
    private lateinit var listPokemonAdapter: ListPokemonAdapter
    private lateinit var viewModel: ListPokemonViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    var arr: ArrayList<Pokemon?> = arrayListOf()
    var pageIndex = 1 // you can pass 1,2,3...
    var isLoading: Boolean = false
    var total: Int = 0

    companion object {
        const val BUNDLE_POKEMON_ID = "BUNDLE_POKEMON_ID"
        const val POKEMON_ID = "POKEMON_ID"
        const val POKEMON_TYPE = "POKEMON_TYPE"

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
        viewModel =
            ViewModelProviders.of(this, ListPokemonFactory(Injection.providerRepository()))
                .get(ListPokemonViewModel::class.java)
        val view: View = inflater.inflate(R.layout.fragment_list_pokemon, container, false)
        initView(view)
        initViewModel()
        initScrollListener();
        return view.rootView
    }

    private fun initScrollListener() {

        viewModel.loadPokemonLoadMore(pageIndex)
        viewModel.pokemonloadMore.observe(this, Observer {

            it.pokemons?.let { it1 -> arr.addAll(it1) }
            listPokemonAdapter.setList(it.pokemons!!)
            total = it.total!!
            Log.d("LINH", "${arr.size}")

        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?
                val dataSize =  arr.size
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == dataSize - 1 && dataSize < total) { //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })

    }

    private fun loadMore() {
        arr.add(null)
        listPokemonAdapter.notifyItemInserted(arr.size - 1)
        val handler = Handler()
        handler.postDelayed(Runnable {
            arr.removeAt(arr.size - 1)
            val scrollPosition: Int = arr.size
            listPokemonAdapter.notifyItemRemoved(scrollPosition)
            //val currentSize = scrollPosition
            pageIndex++
            viewModel.loadPokemonLoadMore(pageIndex)
            viewModel.pokemonloadMore.observe(this, Observer {
                arr.addAll(it.pokemons!!)

                listPokemonAdapter.notifyDataSetChanged()
            })

            isLoading = false
        }, 2000)
    }

    private fun initViewModel() {

//        viewModel.isViewLoading.observe(this, Observer {
//            val visibility = if (it) View.VISIBLE else View.GONE
//            progressBar.visibility = visibility
//        })
//
//        viewModel.onMessageError.observe(this, Observer {
//            layoutError.visibility = View.VISIBLE
//            layoutEmpty.visibility = View.GONE
//
//        })
//        viewModel.isEmptyList.observe(this, Observer {
//            layoutEmpty.visibility = View.VISIBLE
//            layoutError.visibility = View.GONE
//        })

    }


    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.rvListPokemon)
        listPokemonAdapter = ListPokemonAdapter(this.requireActivity()) {
            val intent = Intent(context, DetailPokemonActivity::class.java)
            intent.putExtra(BUNDLE_POKEMON_ID, listPokemonAdapter.getPosition(it).id)
            startActivity(intent)
        }
        layoutManager =
            LinearLayoutManager(context?.applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listPokemonAdapter
        listPokemonAdapter.setLongClickItemCategoryListener(this)

    }

    override fun onLongClickItemCategory(pokemon: Pokemon?) {
        val s = pokemon?.pokemonTypes?.get(0)
        Log.d("MINH", "${s}")
        val args = Bundle()
        args.putString(POKEMON_ID, pokemon?.id)
        args.putString(POKEMON_TYPE, s)
        val newFragment = WeakNessPokemonDialogFragment()
        newFragment.setArguments(args)
        newFragment.show(childFragmentManager, newFragment.javaClass.simpleName)
    }

    override fun buttonClicked(name: String) {
        listPokemonAdapter.filter(name)
    }

    override fun loadPokemon() {
        initViewModel()
    }


}