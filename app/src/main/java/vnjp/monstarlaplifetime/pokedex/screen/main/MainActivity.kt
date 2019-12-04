package vnjp.monstarlaplifetime.pokedex.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.listpokemon.ListPokemonFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    companion object {
        var selectedId: Int = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigationView.selectedItemId = R.id.action_pokemon
        setBottom(bottomNavigationView.selectedItemId)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            setBottom(menuItem.itemId)
        }

    }

    private fun setBottom(itemId: Int): Boolean {
        if (selectedId == itemId) {
            return true
        }
        when (itemId) {

            R.id.action_pokemon -> {
                val fragment = ListPokemonFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.frLayout, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return true
            }

            R.id.action_disk -> {
                //val fragment = ChapterFragment()
                //supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                //.commit()
                return true
            }
            R.id.action_itens -> {
                //val fragment = StoreFragment()
                //supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                //.commit()
                return true
            }
        }
        selectedId = itemId
        return false

    }
}
