package jp.co.soramitsu.androidfoundation.format

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.graphics.drawable.toBitmap

sealed interface ImageValue {
    data class ResImage(val id: Int) : ImageValue

    data class BitmapImage(val bitmap: Bitmap) : ImageValue

    data class DrawableImage(val drawable: Drawable) : ImageValue
}

@Composable
fun ImageValue.retrievePainter(): Painter = when (this) {
    is ImageValue.ResImage ->
        key(id) {
            painterResource(
                id = id,
            )
        }
    is ImageValue.BitmapImage ->
        BitmapPainter(
            image = bitmap.asImageBitmap(),
        )
    is ImageValue.DrawableImage ->
        BitmapPainter(
            image =
            drawable.toBitmap()
                .asImageBitmap(),
        )
}
