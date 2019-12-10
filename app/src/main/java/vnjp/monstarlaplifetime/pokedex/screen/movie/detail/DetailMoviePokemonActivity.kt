package vnjp.monstarlaplifetime.pokedex.screen.movie.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_skill_pokemon.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.movie.ListMovielPokemonFragment

class DetailMoviePokemonActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "CONSOLE"
    }

    private var nameMoviePokemon: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyPokemonTheme);
        setContentView(R.layout.activity_detail_skill_pokemon)
        intent.extras?.let { bundle ->
            nameMoviePokemon = bundle.getString(ListMovielPokemonFragment.BUNDLE_NAME_MOVIE)


        }
        Log.d(TAG, "data ${nameMoviePokemon}")
        initView()
        initEvent()
    }

    private fun initEvent() {
        imbBack.setOnClickListener(this)
    }

    private fun initView() {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imbBack -> {
                finish()
            }
        }

    }
}
