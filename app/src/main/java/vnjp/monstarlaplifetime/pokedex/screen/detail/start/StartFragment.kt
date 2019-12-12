package vnjp.monstarlaplifetime.pokedex.screen.detail.start

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_start.*
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.BasicStatse
import vnjp.monstarlaplifetime.pokedex.screen.detail.detailpokemon.DetailPokemonActivity
import vnjp.monstarlaplifetime.pokedex.screen.dialog.ListWeakNessesAdapter
import java.util.*


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class StartFragment : Fragment() {
    private lateinit var listWeakNessesAdapter: ListWeakNessesAdapter
    private var list: List<BasicStatse> = emptyList()
    private lateinit var basicStatse: List<BasicStatse>
    var count1: Int = 0
    var count2: Int = 0
    var count3: Int = 0
    var count4: Int = 0
    var count5: Int = 0
    var count6: Int = 0
    var currentmax1: Int = 0
    var currentmin1: Int = 0
    var currentmax2: Int = 0
    var currentmin2: Int = 0
    var currentmax3: Int = 0
    var currentmin3: Int = 0
    var currentmax4: Int = 0
    var currentmin4: Int = 0
    var currentmax5: Int = 0
    var currentmin5: Int = 0
    var currentmax6: Int = 0
    var currentmin6: Int = 0
    fun newInstance(): StartFragment {
        return StartFragment()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            if (activity is DetailPokemonActivity) {
                (activity as DetailPokemonActivity).viewModel.pokemonDetail.observe(this, Observer {
                    it.stats?.weaknesses?.let { it1 -> listWeakNessesAdapter.setList(it1) }
                    basicStatse = it.stats?.basicStatses!!
                    tvText1.setText(it.stats!!.basicStatses!!.get(0).name)
                    tvText2.setText(it.stats!!.basicStatses!!.get(1).name)
                    tvText3.setText(it.stats!!.basicStatses!!.get(2).name)
                    tvText4.setText(it.stats!!.basicStatses!!.get(3).name)
                    tvText5.setText(it.stats!!.basicStatses!!.get(4).name)
                    tvText6.setText(it.stats!!.basicStatses!!.get(5).name)
                    currentmax1 = basicStatse.get(0).maxValue!!
                    currentmin1 = basicStatse.get(0).value!!
                    currentmax2 = basicStatse.get(1).maxValue!!
                    currentmin2 = basicStatse.get(1).value!!
                    currentmax3 = basicStatse.get(2).maxValue!!
                    currentmin3 = basicStatse.get(2).value!!
                    currentmax4 = basicStatse.get(3).maxValue!!
                    currentmin4 = basicStatse.get(3).value!!
                    currentmax5 = basicStatse.get(4).maxValue!!
                    currentmin5 = basicStatse.get(4).value!!
                    currentmax6 = basicStatse.get(5).maxValue!!
                    currentmin6 = basicStatse.get(5).value!!
                    Log.d("MINH", "${basicStatse.get(0).maxValue}")

                })

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        initView(view)
        return view.rootView
    }


    private fun initView(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvWeakNesses)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = GridLayoutManager(context, 3)
        listWeakNessesAdapter = ListWeakNessesAdapter(this.requireContext())
        recyclerView?.adapter = listWeakNessesAdapter
        val progress_bar1 = view?.findViewById<ProgressBar>(R.id.progress_bar1)
        val progress_bar2 = view?.findViewById<ProgressBar>(R.id.progress_bar2)
        val progress_bar3 = view?.findViewById<ProgressBar>(R.id.progress_bar3)
        val progress_bar4 = view?.findViewById<ProgressBar>(R.id.progress_bar4)
        val progress_bar5 = view?.findViewById<ProgressBar>(R.id.progress_bar5)
        val progress_bar6 = view?.findViewById<ProgressBar>(R.id.progress_bar6)

        val t1 = Timer()
        val tt1: TimerTask = object : TimerTask() {
            override fun run() {
                count1++
                progress_bar1!!.setProgress(count1)
                progress_bar1.max = currentmax1
                if (count1 === currentmin1)
                    t1.cancel()

            }
        }
        t1.schedule(tt1, 0, 10)
        val t2 = Timer()
        val tt2: TimerTask = object : TimerTask() {
            override fun run() {
                count2++
                progress_bar2!!.setProgress(count2)
                progress_bar2.max = currentmax2
                if (count2 === currentmin2)
                    t2.cancel()

            }
        }
        t2.schedule(tt2, 0, 10)
        val t3 = Timer()
        val tt3: TimerTask = object : TimerTask() {
            override fun run() {
                count3++
                progress_bar3!!.setProgress(count3)
                progress_bar3.max = currentmax3
                if (count3 === currentmin3)
                    t3.cancel()

            }
        }
        t3.schedule(tt3, 0, 10)
        val t4 = Timer()
        val tt4: TimerTask = object : TimerTask() {
            override fun run() {
                count4++
                progress_bar4!!.setProgress(count4)
                progress_bar4.max = currentmax4
                if (count4 === currentmin4)
                    t4.cancel()

            }
        }
        t4.schedule(tt4, 0, 10)
        val t5 = Timer()
        val tt5: TimerTask = object : TimerTask() {
            override fun run() {
                count5++
                progress_bar5!!.setProgress(count5)
                progress_bar5.max = currentmax5
                if (count5 === currentmin5)
                    t5.cancel()

            }
        }
        t5.schedule(tt5, 0, 10)
        val t6 = Timer()
        val tt6: TimerTask = object : TimerTask() {
            override fun run() {
                count6++
                progress_bar6!!.setProgress(count6)
                progress_bar6.max = currentmax6
                if (count6 === currentmin6)
                    t6.cancel()

            }
        }
        t6.schedule(tt6, 0, 10)


    }


}