package jp.co.soramitsu.androidfoundation.intent

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
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
