package vnjp.monstarlaplifetime.pokedex.screen.items

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
import kotlinx.android.synthetic.main.fragment_item_pokemon.*
import kotlinx.android.synthetic.main.fragment_list_pokemon.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Items
import vnjp.monstarlaplifetime.pokedex.screen.items.detail.DetailItemsActivity
import vnjp.monstarlaplifetime.pokedex.screen.listpokemon.ListPokemonFragment
import vnjp.monstarlaplifetime.pokedex.screen.main.MainActivity

class ItemsPokemonFragment : Fragment(), MainActivity.MyInterfacePokemon {
    private lateinit var itemsPokemonAdapter: ItemsPokemonAdapter
    private lateinit var viewModel: ItemsPokemonViewModel
    private var arr: MutableList<Items?> = arrayListOf()
    private lateinit var recyclerView: RecyclerView
    var isLoading: Boolean = false

    fun newInstance(): ItemsPokemonFragment {
        return ItemsPokemonFragment()
    }

    companion object {
        const val BUNDLE_ITEM_ID = "BUNDLE_SKILL_ID"
        var index = 1
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
        val view = inflater.inflate(R.layout.fragment_item_pokemon, container, false)
        viewModel = ViewModelProviders.of(this, ItemsPokemonFactory(Injection.providerRepository()))
            .get(ItemsPokemonViewModel::class.java)
        initView(view)
        initScrollListener()
        initViewModel()

        return view.rootView
    }

    private fun initViewModel() {
        viewModel.loadItems(1)
        viewModel.items.observe(this, Observer {
            arr.addAll(it)
            itemsPokemonAdapter.setListItems(arr)
            Log.d("HIHI", "${arr.size}")
        })
        viewModel.isViewLoading.observe(this, Observer {
            val visibility = if (it) View.VISIBLE else View.GONE
            progressBar1.visibility = visibility
        })
        viewModel.onMessageError.observe(this, Observer {
            layoutError.visibility = View.VISIBLE
            layoutEmpty.visibility = View.GONE
            // textViewError.text= "Error $it"
        })
        viewModel.isEmptyList.observe(this, Observer {
            //            layoutEmpty.visibility = View.VISIBLE
//            layoutError.visibility = View.GONE
        })
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
        itemsPokemonAdapter.notifyItemInserted(arr.size - 1)
        itemsPokemonAdapter.notifyItemRemoved(arr.size)
        arr.removeAt(arr.size - 1)
        val handler = Handler()
        handler.postDelayed(Runnable {
            //arr.removeAt(arr.size - 1)
            ListPokemonFragment.pageIndex++
            viewModel.loadItems(ListPokemonFragment.pageIndex)
            itemsPokemonAdapter.notifyDataSetChanged()
            isLoading = false
        }, 2000)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.rvListItemPokemon)
        itemsPokemonAdapter = ItemsPokemonAdapter(this.requireContext()) {
            val intent = Intent(context, DetailItemsActivity::class.java)
            intent.putExtra(BUNDLE_ITEM_ID, itemsPokemonAdapter.getPositionItems(it).name)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemsPokemonAdapter
    }

    override fun buttonClicked(name: String) {
        itemsPokemonAdapter.filterItems(name)
    }

    override fun loadPokemon() {
        initViewModel()
    }
}