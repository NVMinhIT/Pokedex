package vnjp.monstarlaplifetime.pokedex.screen.movie

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
import vnjp.monstarlaplifetime.pokedex.data.models.Move

class ListMovieAdapter(private val context: Context, private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListMovieAdapter.MyViewHolder>() {

    private var listSkill: List<Move> = emptyList()
    private val pos: Int? = null


    fun setListSkill(list: List<Move>) {
        listSkill = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMovieAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_skill_pokemon, parent, false)

        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return listSkill.size
    }

    override fun onBindViewHolder(holder: ListMovieAdapter.MyViewHolder, position: Int) {
        val skill = listSkill[position]
        holder.bind(skill)
    }

    fun getPositionSkill(position: Int): Move {
        return listSkill[position]

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameSkill: TextView = itemView.findViewById(R.id.tvSkillPokemon)
        private val imageIconSkill: ImageView = itemView.findViewById(R.id.imgIconSkill)

        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }
        }

        fun bind(skill: Move) {
            Picasso.get()
                .load(Uri.parse(skill.type))
                //.placeholder(R.drawable.ic_types_dragon)
                .resize(40, 40)
                .error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imageIconSkill)
            tvNameSkill.text = skill.name
        }


    }
}