package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.screen.detail.DetailPokemonActivity
import vnjp.monstarlaplifetime.pokedex.screen.dialog.WeakNessPokemonDialogFragment
import vnjp.monstarlaplifetime.pokedex.utils.Navigator
import java.io.IOException

class ListPokemonFragment : Fragment(), ListPokemonAdapter.ILongClickItemCategoryListener {


    private lateinit var listPokemonAdapter: ListPokemonAdapter
    private var navigator: Navigator? = null

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
        navigator = Navigator()
        initEvent()

        return view.rootView
    }

    private fun initEvent() {

    }

    private fun initView(view: View) {
        val gson = Gson()
        val model = gson.fromJson(loadJSONFromAssets(), Pokemon::class.java)

        Log.d("Name not found", "" + model)
        val list: List<Pokemon> = listOf(
            Pokemon("#001", "PokemonA", "", "", ""),
            Pokemon("#002", "PokemonB", "", "", ""),
            Pokemon("#003", "PokemonC", "", "", ""),
            Pokemon("#004", "PokemonD", "", "", ""),
            Pokemon("#005", "PokemonE", "", "", ""),
            Pokemon("#006", "PokemonF", "", "", ""),
            Pokemon("#007", "PokemonG", "", "", ""),
            Pokemon("#008", "PokemonH", "", "", ""),
            Pokemon("#009", "PokemonY", "", "", "")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListPokemon)
        listPokemonAdapter = ListPokemonAdapter(this.requireActivity()) {
            val intent = Intent(context, DetailPokemonActivity::class.java)
            intent.putExtra(BUNDLE_STUDENT_ID, listPokemonAdapter.getPosition(it).pokemonId)
            startActivity(intent)

        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listPokemonAdapter
        listPokemonAdapter.setLongClickItemCategoryListener(this)
        listPokemonAdapter.setList(list)

    }

    override fun onLongClickItemCategory(pokemon: Pokemon?) {
        val fragment =
            WeakNessPokemonDialogFragment()
        fragment.show(childFragmentManager, fragment.javaClass.simpleName)
    }

    fun loadJSONFromAssets(): String? {
        var json: String? = null
        try {
            val inputStream = context!!.assets.open("tb_pokemon.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }

}