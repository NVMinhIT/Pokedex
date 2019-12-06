package vnjp.monstarlaplifetime.pokedex.screen.items

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.ItemsPokemon
import vnjp.monstarlaplifetime.pokedex.screen.detail.DetailPokemonActivity
import vnjp.monstarlaplifetime.pokedex.screen.items.detail.DetailItemsActivity

class ItemsPokemonFragment : Fragment() {
    private lateinit var itemsPokemonAdapter: ItemsPokemonAdapter

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
//        val view = inflater.inflate(R.layout.fragment_item_pokemon, container, false)
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_item_pokemon, container, false)
        initView(view)
        return view.rootView
    }

    private fun initView(view: View) {
        val list: List<ItemsPokemon> = listOf(
            ItemsPokemon(0, "", "MasterBall", 1200),
            ItemsPokemon(1, "", "MasterBall", 1200),
            ItemsPokemon(2, "", "MasterBall", 1200),
            ItemsPokemon(3, "", "MasterBall", 1200),
            ItemsPokemon(4, "", "MasterBall", 1200),
            ItemsPokemon(5, "", "MasterBall", 1200),
            ItemsPokemon(6, "", "MasterBall", 1200),
            ItemsPokemon(7, "", "MasterBall", 1200),
            ItemsPokemon(8, "", "MasterBall", 1200),
            ItemsPokemon(9, "", "MasterBall", 1200),
            ItemsPokemon(10, "", "MasterBall", 1200)


        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListItemPokemon)
        itemsPokemonAdapter = ItemsPokemonAdapter(this.requireContext()) {
            val intent = Intent(context, DetailItemsActivity::class.java)
            intent.putExtra(BUNDLE_ITEM_ID, itemsPokemonAdapter.getPositionItems(it).idItem)
            startActivity(intent)
        }
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = itemsPokemonAdapter
        itemsPokemonAdapter.setListItems(list)

    }
}