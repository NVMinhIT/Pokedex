package vnjp.monstarlaplifetime.pokedex.screen.detail.movie

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
import vnjp.monstarlaplifetime.pokedex.data.models.ItemMovieType

class MovieAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private var liItemMovieType: List<ItemMovieType> = emptyList()


    fun setListItemMovieType(list: List<ItemMovieType>) {
        liItemMovieType = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_detail, parent, false)

        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return liItemMovieType.size
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyViewHolder, position: Int) {
        val itemMovie = liItemMovieType[position]
        holder.bind(itemMovie)
    }

    fun getPositionSkill(position: Int): ItemMovieType {
        return liItemMovieType[position]

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameMoviePokemon: TextView = itemView.findViewById(R.id.tvNameMoviePokemon)
        private val tvLevelDetail: TextView = itemView.findViewById(R.id.tvLevelDetail)
        private val imgIconMovie: ImageView = itemView.findViewById(R.id.imgIconMovie)

//        init {
//            itemView.setOnClickListener {
//                itemClick(adapterPosition)
//            }
//        }

        fun bind(itemMovieType: ItemMovieType) {
            Glide.with(context)
                .load(Uri.parse(itemMovieType.imageIconItemMovie))
                .placeholder(R.drawable.ic_types_fight)
                .override(40, 40)
                //.error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imgIconMovie)
            tvNameMoviePokemon.text = itemMovieType.nameItemMovie
            tvLevelDetail.text = itemMovieType.levelItemMovie
        }

    }
}