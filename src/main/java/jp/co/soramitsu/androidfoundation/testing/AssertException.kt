package jp.co.soramitsu.androidfoundation.testing

import org.junit.Assert

@Suppress("TooGenericExceptionCaught")
inline fun <reified T : Exception> assertException(
    expectedMessage: String? = null,
    verifiers: () -> Unit = {},
    action: () -> Unit,
) {
    try {
        action.invoke()
        Assert.fail()
    } catch (e: Exception) {
        Assert.assertEquals(T::class, e::class)
        if (expectedMessage != null) {
            Assert.assertEquals(expectedMessage, e.message)
        }
    } finally {
        verifiers.invoke()
    }
}
