package jp.co.soramitsu.androidfoundation.intent

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import kotlin.system.exitProcess
import kotlin.time.Duration.Companion.seconds

fun restartApplication2(ctx: Context) {
    val launchIntent = ctx.packageManager.getLaunchIntentForPackage(ctx.packageName)
    val pendingStartIntent =
        PendingIntent
            .getActivity(ctx, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val mgr = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    mgr.set(
        AlarmManager.RTC,
        System.currentTimeMillis() + 1.seconds.inWholeMilliseconds,
        pendingStartIntent,
    )
    exitProcess(2)
}

fun Context.restartApplication() {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    val componentName = intent?.component
    val mainIntent = Intent.makeRestartActivityTask(componentName)
    startActivity(mainIntent)
    Runtime.getRuntime().exit(0)
}

fun <T> Intent.getParcelableCompat(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        this.setExtrasClassLoader(clazz.classLoader)
        extras?.getParcelable(key)
    } else {
        getParcelableExtra(key, clazz)
    }
}

fun <T> Bundle.getParcelableCompat(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        this.classLoader = clazz.classLoader
        getParcelable(key)
    } else {
        getParcelable(key, clazz)
    }
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
                PackageManager.PackageInfoFlags.of(0),
            )
        }
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Context.getIntentForPackage(packageName: String) =
    this.packageManager.getLaunchIntentForPackage(
        packageName,
    )

fun Context.openGooglePlay(pn: String? = packageName) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$pn"),
            ),
        )
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$pn"),
            ),
        )
    }
}

fun openSoraTelegramSupportChat(activity: Context?) {
    val appAvailable =
        activity?.let {
            it.isAppAvailableCompat(APP_TELEGRAM) || it.isAppAvailableCompat(APP_TELEGRAM_X)
        } ?: false
    val intent =
        if (appAvailable) {
            Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=$SUPPORT_CHAT_ID"))
        } else {
            Intent(Intent.ACTION_VIEW, Uri.parse(SUPPORT_CHAT_LINK))
        }
    activity?.startActivity(intent)
}
