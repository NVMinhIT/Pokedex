package vnjp.monstarlaplifetime.pokedex.screen.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.Skill
import vnjp.monstarlaplifetime.pokedex.screen.detail.DetailPokemonActivity
import vnjp.monstarlaplifetime.pokedex.screen.movie.detail.DetailSkillPokemonActivity

class ListSkillPokemonFragment : Fragment() {
    private lateinit var listSkillAdapter: ListSkillAdapter
    fun newInstance(): ListSkillPokemonFragment {
        return ListSkillPokemonFragment()
    }

    companion object {
        const val BUNDLE_SKILL_ID = "BUNDLE_SKILL_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_skill, container, false)
        initView(view)
        return view.rootView
    }

    private fun initView(view: View?) {
        val list: List<Skill> = listOf(
            Skill(0, "Bubble", ""),
            Skill(1, "Bubble", ""),
            Skill(2, "Bubble", ""),
            Skill(3, "Bubble",""),
            Skill(4, "Bubble", ""),
            Skill(5, "Bubble", ""),
            Skill(6, "Bubble", ""),
            Skill(7, "Bubble",""),
            Skill(8, "Bubble", ""),
            Skill(9, "Bubble", ""),
            Skill(10, "Bubble", "")


        )
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvListSkill)
        listSkillAdapter = ListSkillAdapter(this.requireContext()) {
            val intent = Intent(context, DetailSkillPokemonActivity::class.java)
            intent.putExtra(BUNDLE_SKILL_ID, listSkillAdapter.getPositionSkill(it).idSkill)
            startActivity(intent)
        }
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = listSkillAdapter
        listSkillAdapter.setListSkill(list)


    }
}