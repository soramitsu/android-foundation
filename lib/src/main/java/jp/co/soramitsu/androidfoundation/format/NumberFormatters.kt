package jp.co.soramitsu.androidfoundation.format

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

private val fiatAbbreviatedFormatter by lazy {
    fiatAbbreviatedFormatter()
}
private val fiatAmountFormatter by lazy {
    FiatFormatter()
}

fun decimalFormatterFor(pattern: String) = DecimalFormat(pattern).apply {
    roundingMode = RoundingMode.FLOOR
}

fun BigDecimal.formatFiatSuffix() = fiatAbbreviatedFormatter.format(this)

private fun fiatAbbreviatedFormatter() = CompoundNumberFormatter(
    abbreviations =
    listOf(
        NumberAbbreviation(BigDecimal.ZERO, BigDecimal.ONE, "", fiatAmountFormatter),
        NumberAbbreviation(BigDecimal.ONE, BigDecimal.ONE, "", fiatAmountFormatter),
        NumberAbbreviation(BigDecimal("1E+3"), BigDecimal.ONE, "", fiatAmountFormatter),
        NumberAbbreviation(
            BigDecimal("1E+6"),
            BigDecimal("1E+6"),
            "M",
            fiatAmountFormatter,
        ),
        NumberAbbreviation(
            BigDecimal("1E+9"),
            BigDecimal("1E+9"),
            "B",
            fiatAmountFormatter,
        ),
        NumberAbbreviation(
            BigDecimal("1E+12"),
            BigDecimal("1E+12"),
            "T",
            fiatAmountFormatter,
        ),
    ),
)
