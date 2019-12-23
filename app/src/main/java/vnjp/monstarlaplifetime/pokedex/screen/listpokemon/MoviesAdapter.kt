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

//class MoviesAdapter(private val context: Context, private val itemClick: (Int) -> Unit) :
//    RecyclerView.Adapter<ListMovieAdapter.MyViewHolder>() {
//
//    private var listSkill: List<Move> = emptyList()
//    private val pos: Int? = null
//
//    companion object {
//
//    }
//    //lọc
//    fun filterMoves(name: String) {
//        if (CommonF.isNullOrEmpty(name)) {
//            setListSkill(listSkill)
//        } else {
//            val orderList: MutableList<Move> =
//                java.util.ArrayList<Move>()
//            for (moves in listSkill) {
//                if (moves.name?.contains(name)!!) {
//                    orderList.add(moves)
//                }
//            }
//            setListSkill(orderList)
//        }
//
//    }
//    fun setListSkill(list: List<Move>) {
//        listSkill = list
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ListMovieAdapter.MyViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_skill_pokemon, parent, false)
//
//        return MyViewHolder(view)
//    }
//
//
//    override fun getItemCount(): Int {
//        return listSkill.size
//    }
//
//    override fun onBindViewHolder(holder: ListMovieAdapter.MyViewHolder, position: Int) {
//        val skill = listSkill[position]
//        holder.bind(skill)
//    }
//
//    fun getPositionSkill(position: Int): Move {
//        return listSkill[position]
//
//    }
//
//
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var isFirst = true
//        private val tvNameSkill: TextView = itemView.findViewById(R.id.tvSkillPokemon)
//        //private val imageIconSkill: ImageView = itemView.findViewById(R.id.imgIconSkill)
//        private val layout: LinearLayout = itemView.findViewById(R.id.contentLinearLayoutIcon)
//
//
//        init {
//            itemView.setOnClickListener {
//                itemClick(adapterPosition)
//            }
//        }
//
//
//
//        fun bind(skill: Move) {
//
//            tvNameSkill.text = skill.name
//            val lsDrawabl: ArrayList<Drawable> = ArrayList()
//            val type = skill.type
//            skill.type?.let {
//                MyApp.pokemonTypeMapping.get(type)?.let { idDrawAble ->
//                    ContextCompat.getDrawable(context, idDrawAble)?.let { icon ->
//                        lsDrawabl.add(icon)
//                    }
//                }
//            }
//            layout.removeAllViews()
//            for (i in lsDrawabl) {
//                val img = ImageView(context)
//                img.setLayoutParams(
//                    LinearLayout.LayoutParams(
//                        CommonF.dpToPx(40),
//                        CommonF.dpToPx(40)
//                    )
//                )
//                img.setImageDrawable(i)
//                layout.addView(img)
//            }
//
//
//        }
//
//
//    }
//}