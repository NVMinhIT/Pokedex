package vnjp.monstarlaplifetime.pokedex.screen.movie

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.MyApp
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Move
import vnjp.monstarlaplifetime.pokedex.utils.CommonF

class ListMovieAdapter(private val context: Context, private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listSkill: List<Move?> = emptyList()
    private val pos: Int? = null

    companion object {
        const val TYPE_MOVIES = 0
        const val TYPE_LOAD = 1
    }

    fun setListSkill(list: List<Move?>) {
        listSkill = list
        notifyDataSetChanged()
    }

    //lọc
    fun filterMoves(name: String) {
        if (CommonF.isNullOrEmpty(name)) {
            setListSkill(listSkill)
        } else {
            val orderList: MutableList<Move?> =
                java.util.ArrayList<Move?>()
            for (moves in listSkill) {
                if (moves?.name?.contains(name)!!) {
                    orderList.add(moves)
                }
            }
            setListSkill(orderList)
        }

    }

    fun getPositionSkill(position: Int): Move {
        return listSkill[position]!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_MOVIES) {
            val view: View =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_skill_pokemon, parent, false)
            MyMovieHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.items_loading, parent, false)
            LoadmoviesHolder(view)
        }

    }

    inner class MyMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameSkill: TextView = itemView.findViewById(R.id.tvSkillPokemon)
        private val layout: LinearLayout = itemView.findViewById(R.id.contentLinearLayoutIcon)

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }
        }


        fun bindData(move: Move) {

            tvNameSkill.text = move.name
            val lsDrawabl: ArrayList<Drawable> = ArrayList()
            val type = move.type
            move.type.let {
                MyApp.pokemonTypeMapping.get(type)?.let { idDrawAble ->
                    ContextCompat.getDrawable(context, idDrawAble)?.let { icon ->
                        lsDrawabl.add(icon)
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

    inner class LoadmoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemCount(): Int {
        return listSkill.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curent = listSkill.get(position)
        curent?.let { (holder as MyMovieHolder).bindData(it) }
    }


}