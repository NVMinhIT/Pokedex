package vnjp.monstarlaplifetime.pokedex.screen.detail.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vnjp.monstarlaplifetime.pokedex.R

class StartFragment : Fragment() {

    fun newInstance(): StartFragment {
        return StartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        return view.rootView
    }
}