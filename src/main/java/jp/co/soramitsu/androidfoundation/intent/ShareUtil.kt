package jp.co.soramitsu.androidfoundation.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment

object ShareUtil {

    private const val mimeText = "text/plain"

    fun Context.openAppSettings() {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }

    fun shareText(c: Context, title: String, body: String) {
        val intent = ShareCompat.IntentBuilder(c)
            .setType(mimeText)
            .setText(body)
            .setChooserTitle(title)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        c.startActivity(Intent.createChooser(intent, title))
    }

    fun shareFile(context: Context, title: String, file: Uri) {
        val mime = context.contentResolver.getType(file)
        val intent = ShareCompat.IntentBuilder(context)
            .setType(mime)
            .setStream(file)
            .setChooserTitle(title)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(Intent.createChooser(intent, title))
    }

    fun shareImageFile(context: Context, title: String, file: Uri, description: String) {
        val intent = ShareCompat.IntentBuilder(context)
            .setType("image/*")
            .setStream(file)
            .setText(description)
            .setChooserTitle(title)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(Intent.createChooser(intent, title))
    }

    fun sendEmail(context: Context, targetEmail: String, title: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            putExtra(Intent.EXTRA_EMAIL, targetEmail)
            type = "message/rfc822"
            data = Uri.parse("mailto:$targetEmail")
        }
        context.startActivity(Intent.createChooser(emailIntent, title))
    }
}
