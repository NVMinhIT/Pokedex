package vnjp.monstarlaplifetime.pokedex.screen.items.detail

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emedinaa.kotlinmvvm.di.Injection
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_items.*
import kotlinx.android.synthetic.main.activity_detail_skill_pokemon.imbBack
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.items.ItemsPokemonFragment

class DetailItemsActivity : AppCompatActivity(), View.OnClickListener {
    var nameItemsPokemon: String? = null
    private lateinit var viewModel: DetailItemsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyItemsTheme);
        setContentView(R.layout.activity_detail_items)
        intent.extras?.let { bundle ->
            nameItemsPokemon = bundle.getString(ItemsPokemonFragment.BUNDLE_ITEM_ID)

        }
        viewModel = ViewModelProviders.of(this, DetailItemsFactory(Injection.providerRepository()))
            .get(DetailItemsViewModel::class.java)
        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {

        nameItemsPokemon?.let {
            viewModel.loadItemsPokemonName(it)
        }

        viewModel.itemsPokemon.observe(this, Observer {
            tvNameItemsPokemon.text = it.name
            tvPriceItems.text = it.price.toString()
            tvDescriptionItems.text = it.effects
            Picasso.get().load(Uri.parse(it.image))
                .centerCrop()
                .resize(120,120)
                .into(imgItemsPokemon)
        })
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
