package jp.co.soramitsu.androidfoundation.format

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.math.absoluteValue
import kotlin.math.pow

private const val DEFAULT_PRECISION = 2

private const val DEFAULT_SCALE_BIG_DECIMAL = 18

private val precisionsMap: Map<Int, BigDecimal> =
    mapOf(
        0 to BigDecimal(1),
        1 to BigDecimal(0.1),
        2 to BigDecimal(0.01),
        3 to BigDecimal(0.001),
        4 to BigDecimal(0.0001),
        5 to BigDecimal(0.00001),
        6 to BigDecimal(0.000001),
        7 to BigDecimal(0.0000001),
        8 to BigDecimal(0.00000001),
        9 to BigDecimal(0.000000001),
        10 to BigDecimal(0.000000001),
        11 to BigDecimal(0.0000000001),
        12 to BigDecimal(0.00000000001),
        13 to BigDecimal(0.000000000001),
        14 to BigDecimal(0.0000000000001),
        15 to BigDecimal(0.00000000000001),
        16 to BigDecimal(0.000000000000001),
        17 to BigDecimal(0.0000000000000001),
        18 to BigDecimal(0.00000000000000001),
    )

private const val DECIMAL_PATTERN_BASE = "###,###."

fun formatDouble(
    num: Double,
    precision: Int = DEFAULT_PRECISION,
    checkFraction: Boolean = true,
): String {
    val newPrecision =
        if (checkFraction) {
            var nubs = num.absoluteValue
            if (nubs > 0 && nubs < 10.0.pow(-precision)) {
                var p = 1
                val scale = DEFAULT_SCALE_BIG_DECIMAL
                while (p < scale) {
                    nubs *= 10
                    if (nubs > 1) {
                        break
                    }
                    p++
                }
                p.coerceAtLeast(precision)
            } else {
                precision
            }
        } else {
            precision
        }
    return decimalFormatterFor(patternWith(newPrecision)).format(num)
}

fun formatBigDecimal(
    num: BigDecimal,
    precision: Int = DEFAULT_PRECISION,
    checkFraction: Boolean = true,
): String {
    val newPrecision =
        if (checkFraction) {
            var nubs = num.abs()
            if (nubs > BigDecimal.ZERO &&
                nubs < precisionsMap[precision.coerceAtMost(DEFAULT_SCALE_BIG_DECIMAL)]
            ) {
                var p = 1
                val scale = nubs.scale()
                while (p < scale) {
                    nubs = nubs.movePointRight(1)
                    if (nubs > BigDecimal.ONE) {
                        break
                    }
                    p++
                }
                p.coerceAtLeast(precision)
            } else {
                precision
            }
        } else {
            precision
        }
    return decimalFormatterFor(patternWith(newPrecision)).format(num)
}

private fun patternWith(precision: Int) = "$DECIMAL_PATTERN_BASE${"#".repeat(precision)}"

val Big100 = BigDecimal.valueOf(100)

fun compareNullDesc(o1: BigDecimal?, o2: BigDecimal?): Int = when {
    o1 == null && o2 == null -> 0
    o1 != null && o2 != null -> o2.compareTo(o1)
    o1 == null -> 1
    else -> -1
}

fun compareNullDesc(o1: Double?, o2: Double?): Int = when {
    o1 == null && o2 == null -> 0
    o1 != null && o2 != null -> o2.compareTo(o1)
    o1 == null -> 1
    else -> -1
}

fun BigInteger.isZero(): Boolean = this.compareTo(BigInteger.ZERO) == 0

fun BigDecimal.isZero(): Boolean = this.compareTo(BigDecimal.ZERO) == 0

fun BigDecimal?.multiplyNullable(decimal: BigDecimal?): BigDecimal? =
    if (this != null && decimal != null) {
        this.multiply(
            decimal,
        )
    } else {
        null
    }

fun BigDecimal.toDoubleInfinite() = this.toDouble().takeIf { converted ->
    converted != Double.NEGATIVE_INFINITY && converted != Double.POSITIVE_INFINITY
} ?: 0.0

fun BigDecimal.equalTo(a: BigDecimal) = this.compareTo(a) == 0

fun BigDecimal.greaterThan(a: BigDecimal) = this.compareTo(a) == 1

fun BigDecimal?.orZero(): BigDecimal = this ?: BigDecimal.ZERO

fun BigDecimal.nullZero(): BigDecimal? = if (this.isZero()) null else this

fun BigDecimal.divideBy(
    divisor: BigDecimal,
    scale: Int? = null,
    defaultScale: Int = DEFAULT_SCALE_BIG_DECIMAL,
): BigDecimal {
    return if (scale == null) {
        val maxScale = kotlin.math.max(this.scale(), divisor.scale()).coerceAtMost(defaultScale)

        if (maxScale != 0) {
            this.divide(divisor, maxScale, RoundingMode.HALF_EVEN)
        } else {
            this.divide(divisor, defaultScale, RoundingMode.HALF_EVEN)
        }
    } else {
        this.divide(divisor, scale, RoundingMode.HALF_EVEN)
    }
}

fun BigDecimal.safeDivide(divisor: BigDecimal, scale: Int? = null): BigDecimal {
    return if (divisor.isZero()) {
        BigDecimal.ZERO
    } else {
        divideBy(divisor, scale)
    }
}
