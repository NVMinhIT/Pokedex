package vnjp.monstarlaplifetime.pokedex.screen.movie.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_skill_pokemon.*
import vnjp.monstarlaplifetime.pokedex.R

class DetailSkillPokemonActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyPokemonTheme);
        setContentView(R.layout.activity_detail_skill_pokemon)
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
