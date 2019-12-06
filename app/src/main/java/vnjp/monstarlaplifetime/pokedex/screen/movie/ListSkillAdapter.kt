package vnjp.monstarlaplifetime.pokedex.screen.movie

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
import vnjp.monstarlaplifetime.pokedex.data.models.Skill

class ListSkillAdapter(private val context: Context, private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListSkillAdapter.MyViewHolder>() {

    private var listSkill: List<Skill> = emptyList()


    fun setListSkill(list: List<Skill>) {
        listSkill = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSkillAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_skill_pokemon, parent, false)

        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return listSkill.size
    }

    override fun onBindViewHolder(holder: ListSkillAdapter.MyViewHolder, position: Int) {
        val skill = listSkill[position]
        holder.bind(skill)
    }

    fun getPositionSkill(position: Int): Skill {
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

        fun bind(skill: Skill) {
            Glide.with(context)
                .load(Uri.parse(skill.imageIconSkill))
                .placeholder(R.drawable.ic_types_dragon)
                .override(40, 40)
                //.error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imageIconSkill)
            tvNameSkill.text = skill.nameSkill
        }


    }
}