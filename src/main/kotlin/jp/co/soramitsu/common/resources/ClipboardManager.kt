package jp.co.soramitsu.common.resources

import android.content.ClipData
import android.content.ClipboardManager

private const val DEFAULT_LABEL = "soramitsu"

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

    fun addToClipboard(text: String, label: String = DEFAULT_LABEL) {
        val clip = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(clip)
    }
}
