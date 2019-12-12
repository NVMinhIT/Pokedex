package vnjp.monstarlaplifetime.pokedex.screen.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.MyApp
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Weakness

class ListWeakNessesAdapter(
    private val context: Context
) : RecyclerView.Adapter<ListWeakNessesAdapter.MyViewHolder>() {
    private var listWeakNesses: List<Weakness> = emptyList()
    fun setList(list: List<Weakness>) {
        listWeakNesses = list
        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListWeakNessesAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weakness_pokemon, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return listWeakNesses.size
    }

    override fun onBindViewHolder(holder: ListWeakNessesAdapter.MyViewHolder, position: Int) {
        val current = listWeakNesses[position]
        holder.bind(current)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var imageView: ImageView = itemView.findViewById(R.id.imgIconWeakNess)
        private var tvIndex: TextView = itemView.findViewById(R.id.tvIndexWeakNess)
        @SuppressLint("SetTextI18n")
        fun bind(weakNesses: Weakness) {

            tvIndex.setText(weakNesses.effect.toString() + "x")
            val lsDrawabl: ArrayList<Drawable> = ArrayList()
            val type = weakNesses.pokemonType
            weakNesses.pokemonType?.let {
                MyApp.pokemonTypeMapping.get(type)?.let { idDrawAble ->
                    ContextCompat.getDrawable(context, idDrawAble)?.let { icon ->
                        lsDrawabl.add(icon)
                    }
                }
            }
            for (i in lsDrawabl) {
                imageView.setImageDrawable(i)
            }
        }

    }

}




