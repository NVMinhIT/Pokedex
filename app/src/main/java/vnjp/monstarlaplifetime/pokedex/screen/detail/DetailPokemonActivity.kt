package vnjp.monstarlaplifetime.pokedex.screen.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_pokemon.*
import vnjp.monstarlaplifetime.pokedex.R

class DetailPokemonActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyPokemonTheme);
        setContentView(R.layout.activity_detail_pokemon)
        initView()
        initEvent()
    }

    private fun initView() {


    }



    private fun initEvent() {
        imbBack.setOnClickListener(this)
        btStart.setOnClickListener(this)
        btEvolution.setOnClickListener(this)
        btMoves.setOnClickListener(this)
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
            }
            R.id.btMoves -> {
                btMoves.setBackgroundResource(R.drawable.bg_button_active)
                btMoves.setTextColor(resources.getColor(R.color.white_view))
                btEvolution.setBackgroundResource(R.drawable.bg_button_noactive)
                btEvolution.setTextColor(resources.getColor(R.color.dark_sky_blue))
                btStart.setBackgroundResource(R.drawable.bg_button_noactive)
                btStart.setTextColor(resources.getColor(R.color.dark_sky_blue))
            }
            else -> {

            }

        }
    }
}
