package vnjp.monstarlaplifetime.pokedex.screen.items

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
import vnjp.monstarlaplifetime.pokedex.screen.items.detail.DetailItemsActivity

class ItemsPokemonFragment : Fragment() {
    private lateinit var itemsPokemonAdapter: ItemsPokemonAdapter
    private lateinit var viewModel: ItemsPokemonViewModel
    fun newInstance(): ItemsPokemonFragment {
        return ItemsPokemonFragment()
    }

    companion object {
        const val BUNDLE_ITEM_ID = "BUNDLE_SKILL_ID"
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
}