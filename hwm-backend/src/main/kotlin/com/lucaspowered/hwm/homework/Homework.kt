package com.lucaspowered.hwm.homework

import kotlinx.serialization.*
import kotlinx.serialization.encoding.*
import java.time.LocalDateTime

@Serializable
data class Homework(val assignmentDetails: String,
                    val dueDate: @Serializable(with = LocalDateTimeSerializer::class) LocalDateTime)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Homework::class)
private object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeString(value.toString())
    }
    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.parse(decoder.decodeString())
    }
}