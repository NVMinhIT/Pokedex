@file:Suppress("UNNECESSARY_SAFE_CALL")

package vnjp.monstarlaplifetime.pokedex.screen.dialog

import android.app.Dialog
import android.content.res.Resources.NotFoundException
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vnjp.monstarlaplifetime.pokedex.R
import vnjp.monstarlaplifetime.pokedex.data.models.WeakNesses
import java.util.*

class WeakNessPokemonDialogFragment : DialogFragment() {

    private lateinit var listWeakNessesAdapter: ListWeakNessesAdapter

    fun newInstance(): WeakNessPokemonDialogFragment {
        return WeakNessPokemonDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_fragment_detail_pokemon, container, false)
        initView(view)
        return view.rootView
    }

    private fun initView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListItemSupport)
        val list: List<WeakNesses> = listOf(
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x"),
            WeakNesses("", "1x")
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        listWeakNessesAdapter = ListWeakNessesAdapter(this.requireActivity())
        recyclerView.adapter = listWeakNessesAdapter
        listWeakNessesAdapter.setList(list)


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        try {
            val dialog = super.onCreateDialog(savedInstanceState)
            Objects.requireNonNull(dialog.window)
                ?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setOnKeyListener { dialog, keyCode, event ->
                // Disable Back key and Search key
                when (keyCode) {
                    KeyEvent.KEYCODE_SEARCH -> true
                    KeyEvent.KEYCODE_BACK -> true
                    else -> false
                }
            }
            return dialog
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onCreateDialog(savedInstanceState)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onResume() {
        super.onResume()
        try {
            val params =
                Objects.requireNonNull(Objects.requireNonNull(dialog)?.window)
                    ?.getAttributes()
            params?.width = resources.getDimensionPixelSize(R.dimen.dialog_with)
            params?.height = resources.getDimensionPixelSize(R.dimen.dialog_height)
            Objects.requireNonNull(dialog!!.window)?.attributes = params
        } catch (e: NotFoundException) {
            e.printStackTrace()
        }
    }
}