package vnjp.monstarlaplifetime.pokedex.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import vnjp.monstarlaplifetime.pokedex.R

class Navigator {
    private var mActivity: AppCompatActivity? = null
    private var mFragment: Fragment? = null

    /**
     * Phương thức khởi tạo lớp Navigator với tham số truyền vào là 1 activity
     *
     * @param activity - activity
     */
    fun Navigator(activity: Activity) {
        mActivity = activity as AppCompatActivity
    }

    /**
     * Phương thức khởi tạo lớp Navigator với tham số truyền vào là 1 fragment
     *
     * @param fragment - fragment
     */
    fun Navigator(fragment: Fragment) {
        mFragment = fragment
        mActivity = (fragment.activity as AppCompatActivity?)!!
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua 1 intent
     *
     * @param intent - intent
     */
    fun startActivity(intent: Intent) {
        mActivity?.startActivity(intent)
        setActivityTransactionAnimation(ActivityTransition.START)
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua 1 intent và hiệu ứng
     *
     * @param intent - intent
     * @param anim   - hiệu ứng
     */
    fun startActivity(intent: Intent, anim: Int) {
        mActivity?.startActivity(intent)
        setActivityTransactionAnimation(ActivityTransition.FINISH)
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua tên class
     *
     * @param clazz - tên_lớp.class
     */
    fun startActivity(clazz: Class<out Activity?>) {
        val intent = Intent(mActivity, clazz)
        startActivity(intent)
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua tên class và hiệu ứng khởi chạy
     *
     * @param clazz - tên_lớp.class
     * @param anim  - hiệu ứng
     */
    fun startActivity(clazz: Class<out Activity?>, anim: Int) {
        val intent = Intent(mActivity, clazz)
        startActivity(intent, anim)
    }

    /**
     * Phương thức khởi chạy 1 activity có tham số là tên activity và 1 bundle
     *
     * @param clazz - tên_lớp.class
     * @param args  - bundle
     */
    fun startActivity(clazz: Class<out Activity?>, args: Bundle?) {
        val intent = Intent(mActivity, clazz)
        intent.putExtras(args!!)
        startActivity(intent)
    }

    /**
     * Phương thức khởi chạy 1 activity và loại bỏ hết các activity khởi stack
     *
     * @param clazz - tên_lớp.class
     */
    fun startActivityAtRoot(clazz: Class<out Activity?>) {
        val intent = Intent(mActivity, clazz)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    /**
     * Phươn thức khởi chạy 1 activity có đợi kq trả về
     *
     * @param intent      - intent
     * @param requestCode - request code
     */
    fun startActivityForResult(intent: Intent, requestCode: Int) {
        mActivity?.startActivityForResult(intent, requestCode)
        setActivityTransactionAnimation(ActivityTransition.START)
    }

    /**
     * Phươn thức khởi chạy 1 activity có đợi kq trả về
     *
     * @param clazz       - tên_lớp.class
     * @param args        - bundle
     * @param requestCode - request code
     */
    fun startActivityForResult(
        clazz: Class<out Activity?>, args: Bundle?,
        requestCode: Int
    ) {
        val intent = Intent(mActivity, clazz)
        intent.putExtras(args!!)
        startActivityForResult(intent, requestCode)
    }

    /**
     * Phương thức finish activity
     */
    fun finishActivity() {
        mActivity?.finish()
        setActivityTransactionAnimation(ActivityTransition.FINISH)
    }

    /**
     * Phương thức finish activity với kết quả trả về
     *
     * @param intent     - intent
     * @param resultCode - request code
     */
    fun finishActivityWithResult(intent: Intent?, resultCode: Int) {
        mActivity?.setResult(resultCode, intent)
        finishActivity()
    }

    /**
     * Điều hướng tới 1 địa chỉ url
     *
     * @param url -địa chỉ web
     */
    fun openUrl(url: String?) {
        if (TextUtils.isEmpty(url) || !Patterns.WEB_URL.matcher(url).matches()) {
            return
        }
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        startActivity(intent)
    }

    /**
     * Thêm 1 fragment lên activity hiện tại
     *
     * @param containerViewId - id của layout muốn hiển thị fragment
     * @param fragment        - fragment muốn hiển thị
     * @param addToBackStack  - cờ có cho vào stack fragment hay không
     * @param animation       - hiệu ứng khi fragment được thêm
     * @param tag             - thẻ của fragment được thêm
     */
    fun addFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        addToBackStack: Boolean, animation: Int, tag: String?
    ) {
        val transaction =
            mActivity?.supportFragmentManager?.beginTransaction()
        if (transaction != null) {
            setFragmentTransactionAnimation(transaction, animation)
        }
        if (addToBackStack) {
            transaction?.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction?.replace(containerViewId, fragment, tag)
        transaction?.commit()
    }

    /**
     * Thêm 1 fragment lên fragment hiện tại
     *
     * @param containerViewId - id của layout muốn hiển thị fragment
     * @param fragment        - fragment muốn hiển thị
     * @param addToBackStack  - cờ có cho vào stack fragment hay không
     * @param animation       - hiệu ứng khi fragment được thêm
     * @param tag             - thẻ của fragment được thêm
     */
    fun goNextChildFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        addToBackStack: Boolean, animation: Int, tag: String?
    ) {
        val transaction =
            mFragment?.childFragmentManager?.beginTransaction()
        if (transaction != null) {
            setFragmentTransactionAnimation(transaction, animation)
        }
        if (addToBackStack) {
            transaction?.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction?.replace(containerViewId, fragment, tag)
        transaction?.commitAllowingStateLoss()
        mFragment?.childFragmentManager?.executePendingTransactions()
    }

    /**
     * Phương thức trở về từ 1 fragment con của 1 fragment đảm bảo luôn có 1 fragment đang hiện
     *
     * @return - trở về/gỡ fragment có thành công hay không
     */
    fun goBackChildFragment(): Boolean {
        val isShowPrevious =
            mFragment?.childFragmentManager?.backStackEntryCount!! > 1
        if (isShowPrevious) {
            mFragment?.childFragmentManager?.popBackStackImmediate()
        }
        //        FragmentManager fm = getFragmentManager();
//        fm.beginTransaction()
//                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                .hide(somefrag)
//                .show()
//                .commit();
        return isShowPrevious
    }

    /**
     * Phương thức truyền hiệu ứng cho fragment
     *
     * @param transaction
     * @param animation
     */
    private fun setFragmentTransactionAnimation(
        transaction: FragmentTransaction,
        @NavigateAnim animation: Int
    ) {
        when (animation) {
//            NavigateAnim.FADED -> transaction.setCustomAnimations(
////                R.anim.fade_in, R.anim.fade_out,
////                R.anim.fade_in, R.anim.fade_out
//            )
            NavigateAnim.RIGHT_LEFT -> transaction.setCustomAnimations(
                R.anim.slide_right_in, R.anim.slide_left_out,
                R.anim.slide_left_in, R.anim.slide_right_out
            )
            NavigateAnim.LEFT_RIGHT -> transaction.setCustomAnimations(
                R.anim.slide_left_in, R.anim.slide_right_out,
                R.anim.slide_right_in, R.anim.slide_left_out
            )
            NavigateAnim.BOTTOM_UP -> transaction.setCustomAnimations(
                R.anim.slide_bottom_in, R.anim.slide_top_out,
                R.anim.slide_top_in, R.anim.slide_bottom_out
            )
            NavigateAnim.NONE -> {
            }
            else -> {
            }
        }
    }

    /**
     * Phương thức truyền hiệu ứng cho activity
     *
     * @param animation
     */
    private fun setActivityTransactionAnimation(@ActivityTransition animation: Int) {
        when (animation) {
            ActivityTransition.START -> mActivity?.overridePendingTransition(
                R.anim.translate_left,
                R.anim.translate_still
            )
            ActivityTransition.FINISH -> mActivity?.overridePendingTransition(
                R.anim.translate_still,
                R.anim.translate_right
            )
            ActivityTransition.NONE -> {
            }
            else -> {
            }
        }
    }

    /**
     * Phương thức ẩn bàn phím
     */
    fun hideKeyboard() {
        val imm = mActivity?.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        var view = mActivity?.currentFocus
        if (view == null) {
            view = View(mActivity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    //    @IntDef(
//        NavigateAnim.RIGHT_LEFT,
//        NavigateAnim.BOTTOM_UP,
//        NavigateAnim.FADED,
//        NavigateAnim.NONE,
//        NavigateAnim.LEFT_RIGHT
//    )
    annotation class NavigateAnim {
        companion object {
            var NONE = 0x00
            var RIGHT_LEFT = 0x01
            var BOTTOM_UP = 0x02
            var FADED = 0x03
            var LEFT_RIGHT = 0x04
        }
    }

    //    @IntDef(
//        ActivityTransition.NONE,
//        ActivityTransition.START,
//        ActivityTransition.FINISH
//    )
    annotation class ActivityTransition {
        companion object {
            var NONE = 0x00
            var START = 0x01
            var FINISH = 0x02
        }
    }
}