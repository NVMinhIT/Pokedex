package vnjp.monstarlaplifetime.pokedex.screen.listpokemon

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Pokemon

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

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    override fun onBindViewHolder(holder: ListPokemonAdapter.MyViewHolder, position: Int) {
        val current = listPokemon[position]

        holder.bind(current)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imgPokemon);
        private val tvTitle: TextView = itemView.findViewById(R.id.tvNamePokemon);
        private val tvCode: TextView = itemView.findViewById(R.id.tvCodePokemon);

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)

            }
        }

        fun bind(pokemon: Pokemon) {
            Glide.with(context)
                .load(Uri.parse(pokemon.pokemonImage))
                .override(50, 50)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)

            tvTitle.text = pokemon.pokemonName
            tvCode.text = pokemon.pokemonId
            this.itemView.setOnLongClickListener {
                longClickItemPokemonListener?.onLongClickItemCategory(pokemon)
                true
            }

        }


    }

    interface ILongClickItemCategoryListener {
        fun onLongClickItemCategory(
            pokemon: Pokemon?
        )
    }
}


