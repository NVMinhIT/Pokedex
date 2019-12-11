package vnjp.monstarlaplifetime.pokedex.utils

import android.content.res.Resources

object CommonF {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
    }
}