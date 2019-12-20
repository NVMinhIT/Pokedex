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

class MoviesAdapter(
    private var context: Context, private val itemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    val TYPE_MOVIE = 0
    val TYPE_LOAD = 1
    private var listPokemon: ArrayList<Pokemon> = arrayListOf()
    private var longClickItemPokemonListener: ILongClickItemCategoryListener? = null
    private var loadMoreListener: OnLoadMoreListener? = null
    var isLoading = false
    var isMoreDataAvailable: Boolean = true

    fun setList(list: ArrayList<Pokemon>) {
        listPokemon = list
        notifyDataSetChanged()
    }

    fun setLongClickItemCategoryListener(categoryListener: ILongClickItemCategoryListener) {
        longClickItemPokemonListener = categoryListener
    }

    fun addAllPokemon(newList: ArrayList<Pokemon>) {
        val lastIndex: Int = listPokemon.size - 1
        listPokemon.addAll(newList)
        notifyItemRangeInserted(lastIndex, newList.size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= itemCount - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true
            loadMoreListener!!.onLoadMore()
        }
        if (getItemViewType(position) == TYPE_MOVIE) {
            (holder as MovieHolder).bindData(listPokemon[position])
        }
        //No else part needed as load holder doesn't bind any data
    }

    override fun getItemViewType(position: Int): Int {
        return if (listPokemon.get(position).name.equals("name")) {
            TYPE_MOVIE
        } else {
            TYPE_LOAD
        }
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

    /* VIEW HOLDERS */
    inner class MovieHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imgPokemon);
        private val tvTitle: TextView = itemView.findViewById(R.id.tvNamePokemon);
        private val tvCode: TextView = itemView.findViewById(R.id.tvCodePokemon);
        private val layout: LinearLayout = itemView.findViewById(R.id.contentLinearLayoutIcon)

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }

        }

        fun bindData(pokemon: Pokemon?) {
            Picasso.get()
                .load(Uri.parse(pokemon?.image))
                .resize(50, 50)
                .centerCrop()
                .into(imageView)

            tvTitle.text = pokemon?.name
            tvCode.text = pokemon?.id
            this.itemView.setOnLongClickListener {
                longClickItemPokemonListener?.onLongClickItemCategory(pokemon)
                true
            }

            val lsDrawabl: ArrayList<Drawable> = ArrayList()
            pokemon?.pokemonTypes?.let {
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

    internal class LoadHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!)

    fun notifyDataChanged() {
        notifyDataSetChanged()
        isLoading = false
    }


    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener?) {
        this.loadMoreListener = loadMoreListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return if (viewType == TYPE_MOVIE) {
            MovieHolder(inflater.inflate(R.layout.item_pokemon, parent, false))
        } else {
            LoadHolder(inflater.inflate(R.layout.items_loading, parent, false))
        }
    }

    interface ILongClickItemCategoryListener {
        fun onLongClickItemCategory(
            pokemon: Pokemon?
        )
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}



