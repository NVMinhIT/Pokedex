package vnjp.monstarlaplifetime.pokedex.screen.items

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Items
import vnjp.monstarlaplifetime.pokedex.utils.CommonF
import java.util.Collections.emptyList


class ItemsPokemonAdapter(private val context: Context, private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listItem: List<Items?> = emptyList()

    companion object {
        const val TYPE_ITEMS = 0
        const val TYPE_LOAD = 1
    }

    fun setListItems(list: List<Items?>) {
        listItem = list
        notifyDataSetChanged()
    }

    fun getPositionItems(position: Int): Items {
        return listItem[position]!!

    }

    //lọc
    fun filterItems(name: String) {
        if (CommonF.isNullOrEmpty(name)) {
            setListItems(listItem)
        } else {
            val orderList: MutableList<Items> =
                java.util.ArrayList<Items>()
            for (item in listItem) {
                if (item?.name?.contains(name)!!) {
                    orderList.add(item)
                }
            }
            setListItems(orderList)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEMS) {
            val view: View =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rvitems_pokemon, parent, false)
            ItemsHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.items_loading, parent, false)
            LoadHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curent = listItem.get(position)
        curent?.let { (holder as ItemsHolder).bind(it) }
    }

    inner class LoadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progressBarLoading)
        }
    }

    inner class ItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameItem: TextView = itemView.findViewById(R.id.tvNameItem)
        private val imageIconItem: ImageView = itemView.findViewById(R.id.imgIconItem)
        private val tvPriceItem: TextView = itemView.findViewById(R.id.tvPriceItem)
        private val imgPrice: ImageView = itemView.findViewById(R.id.imgPrice)

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }
        }

        fun bind(itemsPokemon: Items) {
            Glide.with(context)
                .load(Uri.parse(itemsPokemon.image))
                //.placeholder(R.drawable.ball)
                .override(40, 40)
                //.error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imageIconItem)
            tvNameItem.text = itemsPokemon.name
            tvPriceItem.text = itemsPokemon.price.toString()

        }

    }
}

