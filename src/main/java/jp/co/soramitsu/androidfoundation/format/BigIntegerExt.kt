package jp.co.soramitsu.androidfoundation.format

import java.math.BigInteger

fun BigInteger?.orZero(): BigInteger = this ?: BigInteger.ZERO
