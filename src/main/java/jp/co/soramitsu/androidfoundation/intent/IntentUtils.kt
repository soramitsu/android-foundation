package jp.co.soramitsu.androidfoundation.intent

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import kotlin.system.exitProcess
import kotlin.time.Duration.Companion.seconds

fun restartApplication(ctx: Context) {
    val launchIntent = ctx.packageManager.getLaunchIntentForPackage(ctx.packageName)
    val pendingStartIntent = PendingIntent
        .getActivity(ctx, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val mgr = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1.seconds.inWholeMilliseconds, pendingStartIntent)
    exitProcess(2)
}

const val APP_TELEGRAM = "org.telegram.messenger"
const val APP_TELEGRAM_X = "org.thunderdog.challegram"
const val SUPPORT_CHAT_ID = "soracardofficial"
const val SUPPORT_CHAT_LINK = "https://t.me/$SUPPORT_CHAT_ID"

fun Context.isAppAvailableCompat(appName: String): Boolean {
    return try {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(appName, 0)
        } else {
            packageManager.getPackageInfo(
                appName,
                PackageManager.PackageInfoFlags.of(0)
            )
        }
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun openSoraTelegramSupportChat(activity: Context?) {
    val appAvailable = activity?.let {
        it.isAppAvailableCompat(APP_TELEGRAM) || it.isAppAvailableCompat(APP_TELEGRAM_X)
    } ?: false
    val intent = if (appAvailable) {
        Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=$SUPPORT_CHAT_ID"))
    } else {
        Intent(Intent.ACTION_VIEW, Uri.parse(SUPPORT_CHAT_LINK))
    }
    activity?.startActivity(intent)
}
