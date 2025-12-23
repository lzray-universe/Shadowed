package moe.tachyon.shadowed.dataClass
import kotlinx.serialization.Serializable
import kotlin.let
import kotlin.text.toInt
import kotlin.text.toIntOrNull

@Suppress("unused")
@JvmInline
@Serializable
value class ChatId(val value: Int): Comparable<ChatId>
{
    override fun compareTo(other: ChatId): Int = value.compareTo(other.value)
    override fun toString(): String = value.toString()
    operator fun unaryMinus() = ChatId(-value)
    companion object
    {
        fun String.toChatId() = ChatId(toInt())
        fun String.toChatIdOrNull() = toIntOrNull()?.let(::ChatId)
        fun Number.toChatId() = ChatId(toInt())
    }
}