package jp.co.soramitsu.androidfoundation.format

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface TextValue {
    data class SimpleText(val text: String) : TextValue

    data class StringRes(val id: Int) : TextValue

    data class StringResWithArgs(val id: Int, val payload: Array<Any>) : TextValue {

        override fun equals(other: Any?): Boolean {
            if (other !is StringResWithArgs)
                return false

            return payload.contentEquals(other.payload)
        }

        override fun hashCode(): Int {
            return 137 * id.hashCode() + payload.contentHashCode()
        }
    }
}

@Composable
fun TextValue.retrieveString(): String = when (this) {
    is TextValue.StringRes -> stringResource(id = id)
    is TextValue.StringResWithArgs -> stringResource(id = id, formatArgs = payload)
    is TextValue.SimpleText -> text
}

fun TextValue.retrieveString(context: Context): String = when (this) {
    is TextValue.StringRes -> context.getString(id)
    is TextValue.StringResWithArgs -> context.getString(id, payload)
    is TextValue.SimpleText -> text
}
