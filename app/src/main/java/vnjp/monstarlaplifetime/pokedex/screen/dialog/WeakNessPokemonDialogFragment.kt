@file:Suppress("UNNECESSARY_SAFE_CALL")

package vnjp.monstarlaplifetime.pokedex.screen.dialog

import android.app.Dialog
import android.content.res.Resources.NotFoundException
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.kotlinmvvm.di.Injection
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_detail_pokemon.*
import vnjp.monstarlaplifetime.pokedex.MyApp
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.listpokemon.ListPokemonFragment
import java.util.*


class WeakNessPokemonDialogFragment : DialogFragment() {

    private lateinit var listWeakNessesAdapter: ListWeakNessesAdapter
    private lateinit var viewModel: WeakNessesViewModel
    var idPokemon: String? = null
    var typePokemon: String? = null
    fun newInstance(): WeakNessPokemonDialogFragment {
        return WeakNessPokemonDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mArgs = arguments
        idPokemon = mArgs!!.getString(ListPokemonFragment.POKEMON_ID)
        typePokemon = mArgs.getString(ListPokemonFragment.POKEMON_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_fragment_detail_pokemon, container, false)
        initView(view)
        viewModel = ViewModelProviders.of(this, WeakNessesFactory(Injection.providerRepository()))
            .get(WeakNessesViewModel::class.java)
        initViewModel()
        return view.rootView
    }

    private fun initViewModel() {
        idPokemon?.let { viewModel.loadDetailPokemon(it) }
        viewModel.pokemonDetail.observe(this, androidx.lifecycle.Observer {
            Picasso.get()
                .load(Uri.parse(it.pokemon?.image))
                .resize(50, 50)
                .centerCrop()
                .into(imgPokemon)
            tvNamePokemon.text = it.pokemon?.name
            tvCodePokemon.setText(idPokemon)
//            Picasso.get()
//                .load(Uri.parse(it.pokemon?.pokemonTypes?.get(0)))
//                .resize(50, 50)
//                .centerCrop()
//                .into(imgView1)


        })
        typePokemon?.let {
            viewModel.loadWeakNess(it)
        }
        viewModel.weakness.observe(this, androidx.lifecycle.Observer {
            listWeakNessesAdapter.setList(it)
        })
    }

    private fun initView(view: View) {
        val imgView1: ImageView = view.findViewById(R.id.imgView1)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListItemSupport)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        listWeakNessesAdapter = ListWeakNessesAdapter(this.requireActivity())
        recyclerView.adapter = listWeakNessesAdapter
        val lsDrawabl: ArrayList<Drawable> = ArrayList()
        typePokemon?.let {
            MyApp.pokemonTypeMapping.get(typePokemon!!)?.let { idDrawAble ->
                ContextCompat.getDrawable(this.requireContext(), idDrawAble)?.let { icon ->
                    lsDrawabl.add(icon)
                }
            }
        }
        for (i in lsDrawabl) {
            imgView1.setImageDrawable(i)
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        try {
            val dialog = super.onCreateDialog(savedInstanceState)
            Objects.requireNonNull(dialog.window)
                ?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setOnKeyListener { dialog, keyCode, event ->
                // Disable Back key and Search key
                when (keyCode) {
                    KeyEvent.KEYCODE_SEARCH -> true
                    KeyEvent.KEYCODE_BACK -> true
                    else -> false
                }
            }
            return dialog
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onCreateDialog(savedInstanceState)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onResume() {
        super.onResume()
        try {
            val params =
                Objects.requireNonNull(Objects.requireNonNull(dialog)?.window)
                    ?.getAttributes()
            params?.width = resources.getDimensionPixelSize(R.dimen.dialog_with)
            params?.height = resources.getDimensionPixelSize(R.dimen.dialog_height)
            Objects.requireNonNull(dialog!!.window)?.attributes = params
        } catch (e: NotFoundException) {
            e.printStackTrace()
        }
    }
}