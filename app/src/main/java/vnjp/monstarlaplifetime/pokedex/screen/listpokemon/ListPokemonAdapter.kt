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


class ListPokemonAdapter(
    private var context: Context, private val itemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private var listPokemon: List<Pokemon?> = mutableListOf()
    private var longClickItemPokemonListener: ILongClickItemCategoryListener? = null


    companion object {
        const val TYPE_MOVIE = 0
        const val TYPE_LOAD = 1
        //const val pageIndex = 1
    }


    // set list
    fun setList(list: List<Pokemon?>) {
        listPokemon = list
        notifyDataSetChanged()
    }

    // set long click
    fun setLongClickItemCategoryListener(categoryListener: ILongClickItemCategoryListener) {
        longClickItemPokemonListener = categoryListener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curent = listPokemon.get(position)
        (holder as MovieHolder).bindData(curent)
    }


    fun getPosition(position: Int): Pokemon {
        return listPokemon.get(position)!!

    }

//    override fun getItemViewType(position: Int): Int {
//        return if (listPokemon?.get(position) == null) TYPE_LOAD else TYPE_MOVIE
//    }


//    // add
//     fun setListData(listData: List<Pokemon>?) {
//        if (listPokemon == null) {
//            listPokemon = listOf()
//            listPokemon.add(listData)
//            notifyDataSetChanged()
//        } else {
//            listPokemon.orEmpty()
//            listPokemon.addAll(listData)
//            notifyDataSetChanged()
//        }
//    }


    //lọc
    fun filter(name: String) {
        if (CommonF.isNullOrEmpty(name)) {
            listPokemon.let {
                setList(it)
            }
        } else {
            val orderList: ArrayList<Pokemon?> =
                java.util.ArrayList<Pokemon?>()
            for (item in this.listPokemon) {
                if (item != null) {
                    if (item.name?.contains(name)!!) {
                        orderList.add(item)
                    }
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
                .load(pokemon?.image)
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


    inner class LoadHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progressBarLoading)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_MOVIE) {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
            MovieHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.items_loading, parent, false)
            LoadHolder(view)
        }


    }

    interface ILongClickItemCategoryListener {
        fun onLongClickItemCategory(
            pokemon: Pokemon?
        )
    }


}
