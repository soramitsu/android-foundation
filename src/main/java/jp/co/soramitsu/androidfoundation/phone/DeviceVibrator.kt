package jp.co.soramitsu.androidfoundation.phone

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class DeviceVibrator(
    private val vibrator: Vibrator
) {
    fun makeShortVibration() {
        if (Build.VERSION.SDK_INT >= 26)
            vibrator.vibrate(VibrationEffect.createOneShot(SHORT_VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE))
        else
            vibrator.vibrate(SHORT_VIBRATION_DURATION)
    }

    companion object {
        private const val SHORT_VIBRATION_DURATION = 200L
    }
}
