package vnjp.monstarlaplifetime.pokedex.screen.dialog

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
import vnjp.monstarlaplifetime.pokedex.data.models.Weakness
import vnjp.monstarlaplifetime.pokedex.data.models.Weaknesses

class ListWeakNessesAdapter(
    private val context: Context
) : RecyclerView.Adapter<ListWeakNessesAdapter.MyViewHolder>() {
    private var listWeakNesses: List<Weaknesses> = emptyList()
    fun setList(list: List<Weaknesses>) {
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
        fun bind(weakNesses: Weaknesses) {
            Glide.with(context)
                .load(Uri.parse(weakNesses.imageWeakNesses))
                .override(36, 36)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)

            tvIndex.text = weakNesses.indexWeakNesses

        }

    }

}




