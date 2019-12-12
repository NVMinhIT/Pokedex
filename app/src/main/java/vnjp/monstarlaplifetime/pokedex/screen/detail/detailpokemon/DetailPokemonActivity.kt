package vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emedinaa.kotlinmvvm.di.Injection
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_pokemon.*
import vnjp.monstarlaplifetime.pokedex.MyApp
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Moves
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.screen.detail.evolution.EvolutionsFragment
import vnjp.monstarlaplifetime.pokedex.screen.detail.movie.MovesFragment
import vnjp.monstarlaplifetime.pokedex.screen.detail.start.StartFragment
import vnjp.monstarlaplifetime.pokedex.screen.listpokemon.ListPokemonFragment

@Suppress("DEPRECATION")
class DetailPokemonActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var viewModel: DetailsPokemonViewModel
    private var idPokemon: String? = null
    private var typePokemon: String? = null
    private var pokemon: Pokemon? = null
    private var listMoves: List<Moves> = emptyList()

    companion object {
        var selectedId: Int = 10
        const val ACTION_LIST_MOVES = "ACTION_LIST_MOVES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyPokemonTheme);
        setContentView(R.layout.activity_detail_pokemon)
        intent.extras?.let { bundle ->
            idPokemon = bundle.getString(ListPokemonFragment.BUNDLE_POKEMON_ID)


        }
        viewModel =
            ViewModelProviders.of(this, DetailsPokemonFactory(Injection.providerRepository()))
                .get(DetailsPokemonViewModel::class.java)
        initViewModel()
        initView()
        initEvent()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViewModel() {
        idPokemon?.let {
            viewModel.loadDetailPokemon(it)
        }
        viewModel.pokemonDetail.observe(this, Observer {
            pokemon = it.pokemon
            listMoves = it.moves!!
            setData()
        })
    }

    private fun setData() {
        tvNamePokemon.text = pokemon?.name
        tv_description_pokemon.text = pokemon?.description
        Picasso.get()
            .load(Uri.parse(pokemon?.image))
            .resize(170, 170)
            .centerCrop()
            .into(img_Pokemon)
        //Log.d("MINH", pokemon?.pokemonTypes?.get(0))
        val type = pokemon?.pokemonTypes?.get(0)
        val lsDrawabl: ArrayList<Drawable> = ArrayList()
        type.let {
            MyApp.pokemonTypesMapping.get(type)?.let { idDrawAble ->
                ContextCompat.getDrawable(applicationContext, idDrawAble)?.let { icon ->
                    lsDrawabl.add(icon)
                }
            }
        }
        for (i in lsDrawabl) {
            img_Water.setImageDrawable(i)

        }
    }

    private fun initEvent() {
        btStart.setOnClickListener(this)
        btEvolution.setOnClickListener(this)
        imbBack.setOnClickListener(this)
        btMoves.setOnClickListener(this)
    }

    private fun initView() {
        val btStart = findViewById<Button>(R.id.btStart)
        val btEvolution = findViewById<Button>(R.id.btEvolution)
        val btMoves = findViewById<Button>(R.id.btMoves)
        val imbBack = findViewById<ImageButton>(R.id.imbBack)
    }

    @SuppressLint("ResourceType")
    override fun onClick(p0: View?) {

        when (p0?.id) {

            R.id.btStart -> {

                btStart.setBackgroundResource(R.drawable.bg_button_active)
                btStart.setTextColor(resources.getColor(R.color.white_view))
                btEvolution.setBackgroundResource(R.drawable.bg_button_noactive)
                btEvolution.setTextColor(resources.getColor(R.color.dark_sky_blue))
                btMoves.setBackgroundResource(R.drawable.bg_button_noactive)
                btMoves.setTextColor(resources.getColor(R.color.dark_sky_blue))
                val fragment = StartFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.content_frame_layout, fragment, fragment.javaClass.simpleName)
                    .commit()


            }
            R.id.imbBack -> {
                finish()
            }
            R.id.btEvolution -> {
                btEvolution.setBackgroundResource(R.drawable.bg_button_active)
                btEvolution.setTextColor(resources.getColor(R.color.white_view))
                btStart.setBackgroundResource(R.drawable.bg_button_noactive)
                btStart.setTextColor(resources.getColor(R.color.dark_sky_blue))
                btMoves.setBackgroundResource(R.drawable.bg_button_noactive)
                btMoves.setTextColor(resources.getColor(R.color.dark_sky_blue))
                val fragmentEvolutions = EvolutionsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.content_frame_layout,
                        fragmentEvolutions,
                        fragmentEvolutions.javaClass.simpleName
                    )
                    .commit()
            }
            R.id.btMoves -> {
                btMoves.setBackgroundResource(R.drawable.bg_button_active)
                btMoves.setTextColor(resources.getColor(R.color.white_view))
                btEvolution.setBackgroundResource(R.drawable.bg_button_noactive)
                btEvolution.setTextColor(resources.getColor(R.color.dark_sky_blue))
                btStart.setBackgroundResource(R.drawable.bg_button_noactive)
                btStart.setTextColor(resources.getColor(R.color.dark_sky_blue))
                val fragmentMovie = MovesFragment()
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.content_frame_layout,
                        fragmentMovie,
                        fragmentMovie.javaClass.simpleName
                    )
                    .commit()
            }
        }

    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount === 0) {
            callingActivity
            finish()
        } else {
            fragmentManager.popBackStack()
        }
    }
}
