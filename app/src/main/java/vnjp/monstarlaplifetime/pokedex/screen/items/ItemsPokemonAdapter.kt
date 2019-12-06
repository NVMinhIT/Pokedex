package vnjp.monstarlaplifetime.pokedex.screen.items

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
import vnjp.monstarlaplifetime.pokedex.data.models.ItemsPokemon


class ItemsPokemonAdapter(private val context: Context, private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ItemsPokemonAdapter.MyViewHolder>() {

    private var listItem: List<ItemsPokemon> = emptyList()


    fun setListItems(list: List<ItemsPokemon>) {
        listItem = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsPokemonAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rvitems_pokemon, parent, false)

        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ItemsPokemonAdapter.MyViewHolder, position: Int) {
        val items = listItem[position]
        holder.bind(items)
    }

    fun getPositionItems(position: Int): ItemsPokemon {
        return listItem[position]

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameItem: TextView = itemView.findViewById(R.id.tvNameItem)
        private val imageIconItem: ImageView = itemView.findViewById(R.id.imgIconItem)
        private val tvPriceItem: TextView = itemView.findViewById(R.id.tvPriceItem)
        private val imgPrice: ImageView = itemView.findViewById(R.id.imgPrice)

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }
        }

        fun bind(itemsPokemon: ItemsPokemon) {
            Glide.with(context)
                .load(Uri.parse(itemsPokemon.imageIconItem))
                .placeholder(R.drawable.ball)
                .override(40, 40)
                //.error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imageIconItem)

        }


    }
}