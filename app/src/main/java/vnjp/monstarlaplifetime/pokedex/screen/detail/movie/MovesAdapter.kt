package vnjp.monstarlaplifetime.pokedex.screen.detail.movie

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
import vnjp.monstarlaplifetime.pokedex.data.models.Moves
import vnjp.monstarlaplifetime.pokedex.utils.CommonF

class MovesAdapter(private val context: Context) :
    RecyclerView.Adapter<MovesAdapter.MyViewHolder>() {

    private var liItemMovieType: List<Moves> = emptyList()


    fun setListItemMovieType(list: List<Moves>) {
        liItemMovieType = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovesAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_detail, parent, false)

        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return liItemMovieType.size
    }

    override fun onBindViewHolder(holder: MovesAdapter.MyViewHolder, position: Int) {
        val itemMovie = liItemMovieType[position]
        holder.bind(itemMovie)
    }

    fun getPositionSkill(position: Int): Moves {
        return liItemMovieType[position]

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isFirst = true
        private val tvNameMoviePokemon: TextView = itemView.findViewById(R.id.tvNameMoviePokemon)
        private val tvLevelDetail: TextView = itemView.findViewById(R.id.tvNumberLevel)
        private val layout: LinearLayout = itemView.findViewById(R.id.contentLinearLayoutIcon)

//        init {
//            itemView.setOnClickListener {
//                itemClick(adapterPosition)
//            }
//        }

        fun bind(itemMovieType: Moves) {

            tvNameMoviePokemon.text = itemMovieType.name
            tvLevelDetail.text = itemMovieType.level.toString()
            val lsDrawabl: ArrayList<Drawable> = ArrayList()
            val type = itemMovieType.type
            itemMovieType.type?.let {
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
}