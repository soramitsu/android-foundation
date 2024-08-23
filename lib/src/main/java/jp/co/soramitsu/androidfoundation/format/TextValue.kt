package jp.co.soramitsu.androidfoundation.format

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource

sealed interface TextValue {
    data class SimpleText(val text: String) : TextValue

    data class StringRes(val id: Int) : TextValue

    data class StringResWithArgs(val id: Int, val payload: Array<Any>) : TextValue {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as StringResWithArgs

            if (id != other.id) return false
            if (!payload.contentEquals(other.payload)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + payload.contentHashCode()
            return result
        }
    }

    data class StringPluralWithArgs(val id: Int, val amount: Int, val payload: Array<Any>) : TextValue {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as StringPluralWithArgs

            if (id != other.id) return false
            if (amount != other.amount) return false
            if (!payload.contentEquals(other.payload)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + amount
            result = 31 * result + payload.contentHashCode()
            return result
        }
    }
}

@Composable
fun TextValue.retrieveString(): String = when (this) {
    is TextValue.StringRes -> stringResource(id = id)
    is TextValue.StringResWithArgs -> stringResource(id = id, formatArgs = payload)
    is TextValue.SimpleText -> text
    is TextValue.StringPluralWithArgs ->
        pluralStringResource(
            id = id,
            count = amount,
            formatArgs = payload,
        )
}

fun TextValue.retrieveString(context: Context): String = when (this) {
    is TextValue.StringRes -> context.getString(id)
    is TextValue.StringResWithArgs -> context.getString(id, *payload)
    is TextValue.SimpleText -> text
    is TextValue.StringPluralWithArgs -> context.resources.getQuantityString(
        id,
        amount,
        *payload,
    )
}
