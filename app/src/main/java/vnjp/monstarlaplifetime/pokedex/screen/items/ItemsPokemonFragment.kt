package vnjp.monstarlaplifetime.pokedex.screen.items

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
import kotlinx.android.synthetic.main.fragment_item_pokemon.*
import kotlinx.android.synthetic.main.fragment_list_pokemon.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.items.detail.DetailItemsActivity
import vnjp.monstarlaplifetime.pokedex.screen.main.MainActivity

class ItemsPokemonFragment : Fragment(), MainActivity.MyInterfacePokemon {
    private lateinit var itemsPokemonAdapter: ItemsPokemonAdapter
    private lateinit var viewModel: ItemsPokemonViewModel
    fun newInstance(): ItemsPokemonFragment {
        return ItemsPokemonFragment()
    }

    companion object {
        const val BUNDLE_ITEM_ID = "BUNDLE_SKILL_ID"
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
        initViewModel()
        initView(view)
        return view.rootView
    }

    private fun initViewModel() {
        viewModel.loadItems()
        viewModel.items.observe(this, Observer {
            itemsPokemonAdapter.setListItems(it)
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
            layoutEmpty.visibility = View.VISIBLE
            layoutError.visibility = View.GONE
        })
    }

    private fun initView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListItemPokemon)
        itemsPokemonAdapter = ItemsPokemonAdapter(this.requireContext()) {
            val intent = Intent(context, DetailItemsActivity::class.java)
            intent.putExtra(BUNDLE_ITEM_ID, itemsPokemonAdapter.getPositionItems(it).name)
            startActivity(intent)
        }
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = itemsPokemonAdapter
    }

    override fun buttonClicked(name: String) {
        itemsPokemonAdapter.filterItems(name)
    }

    override fun loadPokemon() {
        initViewModel()
    }
}