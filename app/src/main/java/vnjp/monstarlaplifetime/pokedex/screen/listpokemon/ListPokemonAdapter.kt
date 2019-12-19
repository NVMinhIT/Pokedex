package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import vnjp.monstarlaplifetime.pokedex.MyApp
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon
import vnjp.monstarlaplifetime.pokedex.utils.CommonF


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ListPokemonAdapter(private var context: Context, private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListPokemonAdapter.MyViewHolder>() {
    private var listPokemon: ArrayList<Pokemon> = arrayListOf()
    private var longClickItemPokemonListener: ILongClickItemCategoryListener? = null

    val TYPE_FOOTER = 1
    val TYPE_ITEM = 2
    private lateinit var view: View

    protected var showLoader = true

    fun setList(list: ArrayList<Pokemon>) {
        listPokemon = list
        notifyDataSetChanged()
    }

    fun setLongClickItemCategoryListener(categoryListener: ILongClickItemCategoryListener) {
        longClickItemPokemonListener = categoryListener
    }


    fun addAll(newList: ArrayList<Pokemon>) {
        val lastIndex: Int = listPokemon.size - 1
        listPokemon.addAll(newList)
        notifyItemRangeInserted(lastIndex, newList.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPokemonAdapter.MyViewHolder {
        if (viewType == TYPE_ITEM) {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
            return MyViewHolder(view)
        } else {
            if (viewType == TYPE_FOOTER) {
                val v: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.items_loading, parent, false)
                return MyViewHolder(v)
            }
        }
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
            val orderList: ArrayList<Pokemon> =
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

    private fun getItem(position: Int): Pokemon {

        return listPokemon.get(position)


    }

    override fun getItemViewType(position: Int): Int {
        if ((position == listPokemon.size - 1) && showLoader) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    fun removeItem(position: Int) {
        listPokemon.removeAt(position)
        notifyItemRemoved(position)
    }

    fun showLoading(status: Boolean) {
        showLoader = status
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos = 0
        private lateinit var pokemon: Pokemon
        private val imageView: ImageView = itemView.findViewById(R.id.imgPokemon);
        private val tvTitle: TextView = itemView.findViewById(R.id.tvNamePokemon);
        private val tvCode: TextView = itemView.findViewById(R.id.tvCodePokemon);
        private val layout: LinearLayout = itemView.findViewById(R.id.contentLinearLayoutIcon)
         var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progressBar)
        }

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }

        }

        fun setPosition(pos: Int) {
            this.pos = pos
        }

        fun setSingleBean(pokemon: Pokemon) {
            this.pokemon = pokemon
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


