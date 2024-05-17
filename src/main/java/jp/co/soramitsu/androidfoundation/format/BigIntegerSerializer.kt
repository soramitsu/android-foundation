package jp.co.soramitsu.androidfoundation.format

import java.math.BigInteger
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive

typealias JsonBigInteger =
    @Serializable(with = BigIntegerSerializer::class)
    BigInteger?

object BigIntegerSerializer : KSerializer<BigInteger?> {

    override val descriptor =
        PrimitiveSerialDescriptor("java.math.BigInteger", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): BigInteger? {
        return runCatching {
            if (decoder is JsonDecoder) {
                decoder.decodeJsonElement().jsonPrimitive.content.toBigInteger()
            } else {
                decoder.decodeString().toBigInteger()
            }
        }.getOrNull()
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: BigInteger?) {
        return if (value == null) {
            encoder.encodeNull()
        } else {
            encoder.encodeString(value.toString())
        }
    }
}
