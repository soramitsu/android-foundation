package jp.co.soramitsu.androidfoundation.phone

import android.content.ClipData
import android.content.ClipboardManager

private const val DefaultLabel = "soramitsu"

class ClipboardManager(
    private val clipboardManager: ClipboardManager
) {

    fun getFromClipboard(): String? = with(clipboardManager) {
        when {
            !hasPrimaryClip() -> null
            primaryClipDescription?.hasMimeType("text/*") != true -> null
            else -> primaryClip?.getItemAt(0)?.text.toString()
        }
    }

    fun addToClipboard(text: String, label: String = DefaultLabel) {
        val clip = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(clip)
    }
}
