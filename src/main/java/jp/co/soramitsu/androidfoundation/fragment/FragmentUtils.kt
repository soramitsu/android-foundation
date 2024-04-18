package jp.co.soramitsu.androidfoundation.fragment

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment

fun Fragment.onBackPressed(block: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                block()
            }
        },
    )
}

fun ComponentActivity.onActivityBackPressed(block: () -> Unit) {
    onBackPressedDispatcher.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                block()
            }
        },
    )
}

inline fun <T : Fragment> T.withArgs(argInitializer: (arguments: Bundle) -> Unit): T {
    val args = arguments ?: Bundle()
    argInitializer.invoke(args)
    arguments = args
    return this
}

inline fun <T : Fragment> T.applyArgs(argInitializer: Bundle.() -> Unit): T {
    arguments = (arguments ?: Bundle()).apply(argInitializer)
    return this
}

fun Fragment.safeStartActivity(intent: Intent, errorMessage: CharSequence) {
    try {
        startActivity(intent)
    } catch (exception: ActivityNotFoundException) {
        Toast.makeText(
            requireContext(),
            errorMessage,
            Toast.LENGTH_LONG,
        ).show()
    }
}

fun Context.safeStartActivity(intent: Intent, errorMessage: CharSequence) {
    try {
        startActivity(intent)
    } catch (exception: ActivityNotFoundException) {
        Toast.makeText(
            this,
            errorMessage,
            Toast.LENGTH_LONG,
        ).show()
    }
}

fun Fragment.dp2px(dp: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    requireContext().resources.displayMetrics,
).toInt()

fun Fragment.dpRes2px(@DimenRes res: Int): Int =
    requireContext().resources.getDimensionPixelSize(res)

fun <T : Parcelable> Fragment.requireParcelable(key: String): T {
    return requireNotNull(requireArguments().getParcelable(key)) { "Argument [$key] not found" }
}

fun Fragment.hideSoftKeyboard() {
    requireActivity().hideSoftKeyboard()
}

fun Fragment.openSoftKeyboard(view: View) {
    requireActivity().openSoftKeyboard(view)
}

fun Context.hideSoftKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_IMPLICIT,
        0,
    )
}

fun Context.openSoftKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_IMPLICIT,
        0,
    )
}
