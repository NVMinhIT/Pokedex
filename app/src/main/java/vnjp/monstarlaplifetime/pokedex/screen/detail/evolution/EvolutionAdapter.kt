package vnjp.monstarlaplifetime.pokedex.screen.detail.evolution

import android.annotation.SuppressLint
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
import vnjp.monstarlaplifetime.pokedex.data.models.Evolution

class EvolutionAdapter(private val context: Context) :
    RecyclerView.Adapter<EvolutionAdapter.MyViewHolder>() {

    private var listEvolution: List<Evolution> = emptyList()

    fun setListEvolution(list: List<Evolution>) {

        listEvolution = list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EvolutionAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_evolution, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listEvolution.size
    }

    override fun onBindViewHolder(holder: EvolutionAdapter.MyViewHolder, position: Int) {
        val evolution = listEvolution[position]
        holder.bind(evolution)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgViewPokemon: ImageView = itemView.findViewById(R.id.imgViewPokemon)
        val tvNamePokemon: TextView = itemView.findViewById(R.id.tvNamePokemon)
        val imgViewPokemonEvolution: ImageView = itemView.findViewById(R.id.imgViewPokemonEvolution)
        val tvEvolutionPokemon: TextView = itemView.findViewById(R.id.tvEvolutionPokemon)
        val tvLevel: TextView = itemView.findViewById(R.id.tvLevel)

        @SuppressLint("SetTextI18n")
        fun bind(evolution: Evolution) {
            Picasso.get()
                .load(Uri.parse(evolution.pokemonImage))
                .resize(40, 40)
                //.error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imgViewPokemon)
            Picasso.get()
                .load(Uri.parse(evolution.evolutionpokemonImage))
                .resize(40, 40)
                //.error(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(imgViewPokemonEvolution)
            tvNamePokemon.text = evolution.pokemonName
            tvEvolutionPokemon.text = evolution.evolutionPokemonName
            tvLevel.setText("Lv." + "" + evolution.evolutionLevel.toString() + "")
        }

    }
}