package vnjp.monstarlaplifetime.pokedex.screen.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_pokemon.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.detail.evolution.EvolutionsFragment
import vnjp.monstarlaplifetime.pokedex.screen.detail.movie.MovieFragment
import vnjp.monstarlaplifetime.pokedex.screen.detail.start.StartFragment

@Suppress("DEPRECATION")
class DetailPokemonActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var selectedId: Int = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyPokemonTheme);
        setContentView(R.layout.activity_detail_pokemon1)
        initView()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val actionBar = supportActionBar
//        actionBar!!.title = "Squirtle"
        //actionBar.setDisplayShowHomeEnabled(true)


    }


    private fun initView() {
        selectedId = R.id.btStart
//        val imbBack = findViewById<ImageButton>(R.id.imbBackButton)
        val btStart = findViewById<Button>(R.id.btStart)
        val btEvolution = findViewById<Button>(R.id.btEvolution)
        val btMoves = findViewById<Button>(R.id.btMoves)
        //imbBack.setOnClickListener(this)
        btStart.setOnClickListener(this)
        btEvolution.setOnClickListener(this)
        btMoves.setOnClickListener(this)
    }

    @SuppressLint("ResourceType")
    override fun onClick(p0: View?) {

        when (p0?.id) {

            R.id.btStart -> {
                selectedId
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
                val fragmentMovie = MovieFragment()
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
