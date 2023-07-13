package jp.co.soramitsu.androidfoundation.format

import java.math.BigDecimal

class FiatFormatter : NumberFormatter {
    companion object {
        private const val FIAT_DECIMAL_PATTERN = "#,##0.00"
    }

    override fun format(number: BigDecimal): String {
        val delegate = decimalFormatterFor(FIAT_DECIMAL_PATTERN)
        val decimalSeparator = delegate.decimalFormatSymbols.decimalSeparator
        val leadingZeros = Regex(decimalSeparator + "00$")
        return delegate.format(number).replace(leadingZeros, "")
    }
}
