package vnjp.monstarlaplifetime.pokedex.screen.detail.movie

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Moves

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
        private val tvNameMoviePokemon: TextView = itemView.findViewById(R.id.tvNameMoviePokemon)
        private val tvLevelDetail: TextView = itemView.findViewById(R.id.tvNumberLevel)
        private val imgIconMovie: ImageView = itemView.findViewById(R.id.imgIconMovie)

//        init {
//            itemView.setOnClickListener {
//                itemClick(adapterPosition)
//            }
//        }

        fun bind(itemMovieType: Moves) {
            Picasso.get()
                .load(Uri.parse(itemMovieType.type))
                .resize(40, 40)
                //.error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imgIconMovie)
            tvNameMoviePokemon.text = itemMovieType.name
            tvLevelDetail.text = itemMovieType.level.toString()
        }

    }
}