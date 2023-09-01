package jp.co.soramitsu.androidfoundation.phone

import android.os.Vibrator

class DeviceVibrator(
    private val vibrator: Vibrator
) {
    fun makeShortVibration() {
        vibrator.vibrate(SHORT_VIBRATION_DURATION)
    }

    companion object {
        private const val SHORT_VIBRATION_DURATION = 200L
    }
}