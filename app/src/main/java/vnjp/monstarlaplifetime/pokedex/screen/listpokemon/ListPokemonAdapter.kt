package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import vnjp.monstarlaplifetime.pokedex.MyApp
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.utils.CommonF

class ListPokemonAdapter(private val context: Context, private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListPokemonAdapter.MyViewHolder>() {
    private var listPokemon: List<Pokemon> = emptyList()
    private var longClickItemPokemonListener: ILongClickItemCategoryListener? = null

    fun setList(list: List<Pokemon>) {
        listPokemon = list
        notifyDataSetChanged()
    }

    fun setLongClickItemCategoryListener(categoryListener: ILongClickItemCategoryListener) {
        longClickItemPokemonListener = categoryListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPokemonAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return MyViewHolder(view)
    }

    fun getPosition(position: Int): Pokemon {
        return listPokemon[position]

    }

    //lọc
    fun filter(name: String) {
        if (CommonF.isNullOrEmpty(name)) {
            setList(listPokemon)
        } else {
            val orderList: MutableList<Pokemon> =
                java.util.ArrayList<Pokemon>()
            for (item in listPokemon) {
                if (item.name?.contains(name)!!) {
                    orderList.add(item)
                }
            }
            setList(orderList)
        }

    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    override fun onBindViewHolder(holder: ListPokemonAdapter.MyViewHolder, position: Int) {
        val current = listPokemon[position]
        holder.bind(current)
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstTime = true
        private val imageView: ImageView = itemView.findViewById(R.id.imgPokemon);
        private val tvTitle: TextView = itemView.findViewById(R.id.tvNamePokemon);
        private val tvCode: TextView = itemView.findViewById(R.id.tvCodePokemon);
        private val layout: LinearLayout = itemView.findViewById(R.id.contentLinearLayoutIcon)

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }
        }

        fun bind(pokemon: Pokemon) {
            Picasso.get()
                .load(Uri.parse(pokemon.image))
                .resize(50, 50)
                .centerCrop()
                .into(imageView)

            tvTitle.text = pokemon.name
            tvCode.text = pokemon.id
            this.itemView.setOnLongClickListener {
                longClickItemPokemonListener?.onLongClickItemCategory(pokemon)
                true
            }

            val lsDrawabl: ArrayList<Drawable> = ArrayList()
            pokemon.pokemonTypes?.let {
                for (stringType in it) {
                    MyApp.pokemonTypeMapping.get(stringType)?.let { idDrawAble ->
                        ContextCompat.getDrawable(context, idDrawAble)?.let { icon ->
                            lsDrawabl.add(icon)
                        }
                    }
                }
            }

            layout.removeAllViews()
            for (i in lsDrawabl) {
                val img = ImageView(context)
                img.setLayoutParams(
                    LinearLayout.LayoutParams(
                        CommonF.dpToPx(40),
                        CommonF.dpToPx(40)
                    )
                )
                img.setImageDrawable(i)
                layout.addView(img)
            }

        }
    }

    interface ILongClickItemCategoryListener {
        fun onLongClickItemCategory(
            pokemon: Pokemon?
        )
    }
}


