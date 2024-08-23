package jp.co.soramitsu.androidfoundation.format

import java.math.BigDecimal

interface NumberFormatter {
    fun format(number: BigDecimal): String
}
