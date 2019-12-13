package vnjp.monstarlaplifetime.pokedex.screen.movie.detail

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emedinaa.kotlinmvvm.di.Injection
import kotlinx.android.synthetic.main.activity_detail_skill_pokemon.*
import vnjp.monstarlaplifetime.pokedex.MyApp
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.movie.ListMovielPokemonFragment

class DetailMoviePokemonActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "CONSOLE"
    }

    private lateinit var viewModel: DetailMovieViewModel


    private var nameMoviePokemon: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyPokemonTheme);
        setContentView(R.layout.activity_detail_skill_pokemon)
        intent.extras?.let { bundle ->
            nameMoviePokemon = bundle.getString(ListMovielPokemonFragment.BUNDLE_NAME_MOVIE)

        }
        viewModel = ViewModelProviders.of(this, DetailMovieFactory(Injection.providerRepository()))
            .get(DetailMovieViewModel::class.java)
        nameMoviePokemon?.let {
            viewModel.loadPokemonByName(it)
        }
        initView()
        initEvent()
    }


    private fun initEvent() {
        imbBack.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val tv_Name_Pokemon: TextView = findViewById(R.id.tv_Name_Pokemon)
        val tv_description_skill: TextView = findViewById(R.id.tv_description_skill)
        val imgIcon: ImageView = findViewById(R.id.img_Pokemon)
        val imIconType: ImageView = findViewById(R.id.img_Water)
        val tvBasePower: TextView = findViewById(R.id.tvBasePower)
        val tvAccuracy: TextView = findViewById(R.id.tvAccuracy)
        val tvPP: TextView = findViewById(R.id.tvPP)

        viewModel.moviesPokemon.observe(this, Observer {
            tv_Name_Pokemon.text = it.name
            tv_description_skill.text = it.effects
            tvBasePower.text = it.power.toString()
            tvAccuracy.setText(it.accuracy.toString() + "%")
            tvPP.text = it.pp.toString()
            val lsDrawabl: ArrayList<Drawable> = ArrayList()
            val typesPokemon = it.type
            typesPokemon.let {
                MyApp.pokemonTypeMapping.get(typesPokemon)?.let { idDrawAble ->
                    ContextCompat.getDrawable(applicationContext, idDrawAble)?.let { icon ->
                        lsDrawabl.add(icon)
                    }
                }
            }
            for (i in lsDrawabl) {
                imgIcon.setImageDrawable(i)

            }
            //icon type pokemon
            val lsDrawable: ArrayList<Drawable> = ArrayList()

            typesPokemon.let {
                MyApp.pokemonTypesMapping.get(typesPokemon)?.let { idDrawableIcon ->
                    ContextCompat.getDrawable(applicationContext, idDrawableIcon)?.let { iconType ->
                        lsDrawable.add(iconType)
                    }
                }
            }
            for (i in lsDrawable) {
                imIconType.setImageDrawable(i)

            }
            //name icon type pokemon
            val stringname: ArrayList<String> = ArrayList()
            typesPokemon.let {
                MyApp.pokemonTypesMappingString.get(typesPokemon)?.let { idName ->
                    stringname.add(idName)
                }
            }

            for (st in stringname) {
                //tvName.setText(st)
            }

        })


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imbBack -> {
                finish()
            }
        }

    }
}
