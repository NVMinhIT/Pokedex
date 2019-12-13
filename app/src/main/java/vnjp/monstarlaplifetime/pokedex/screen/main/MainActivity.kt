package vnjp.monstarlaplifetime.pokedex.screen.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.screen.items.ItemsPokemonFragment
import vnjp.monstarlaplifetime.pokedex.screen.listpokemon.ListPokemonFragment
import vnjp.monstarlaplifetime.pokedex.screen.movie.ListMovielPokemonFragment
import vnjp.monstarlaplifetime.pokedex.utils.CommonF


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private var myInterfaces: MyInterfacePokemon? = null


    companion object {
        var selectedId: Int = 10
    }

    fun setMyinterFace(myInterface: MyInterfacePokemon) {
        myInterfaces = myInterface
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initEvent()

    }

    private fun initEvent() {
        val edtSearchPokemon = findViewById<EditText>(R.id.edtSearchPokemon)
        edtSearchPokemon.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                //  listPokemonAdapter.filter(s.toString())
                myInterfaces?.buttonClicked(s.toString())
                if (CommonF.isNullOrEmpty(s.toString())) {
                    myInterfaces?.loadPokemon()
                }

            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        tvTitle.text = "Pokemon"
        bottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigationView.selectedItemId = R.id.action_pokemon
        setBottom(bottomNavigationView.selectedItemId)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            setBottom(menuItem.itemId)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setBottom(itemId: Int): Boolean {
        if (selectedId == itemId) {
            return true
        }
        when (itemId) {

            R.id.action_pokemon -> {
                tvTitle?.text = "Pokemon"
                val fragment = ListPokemonFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frLayout, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }

            R.id.action_disk -> {
                tvTitle.text = "Movie"
                val fragmentList = ListMovielPokemonFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frLayout, fragmentList, fragmentList.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.action_itens -> {
                tvTitle.text = "Items"
                val fragmentItems = ItemsPokemonFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frLayout, fragmentItems, fragmentItems.javaClass.simpleName)
                    .commit()
                return true
            }
        }
        selectedId = itemId
        return false

    }

    interface MyInterfacePokemon {
        fun buttonClicked(name: String)
        fun loadPokemon()
    }



}
