package vnjp.monstarlaplifetime.pokedex.screen.movie.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emedinaa.kotlinmvvm.di.Injection
import kotlinx.android.synthetic.main.activity_detail_skill_pokemon.*
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

    private fun initView() {
        val tv_Name_Pokemon: TextView = findViewById(R.id.tv_Name_Pokemon)
        val tv_description_skill: TextView = findViewById(R.id.tv_description_skill)
        val tvBasePower: TextView = findViewById(R.id.tvBasePower)
        val tvAccuracy: TextView = findViewById(R.id.tvAccuracy)
        val tvPP: TextView = findViewById(R.id.tvPP)

        viewModel.moviesPokemon.observe(this, Observer {
            tv_Name_Pokemon.text = it.name
            tv_description_skill.text = it.effects
            tvBasePower.text = it.power.toString()
            tvAccuracy.text = it.accuracy.toString()
            tvPP.text = it.pp.toString()

            Log.d(TAG, "${it}")

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
