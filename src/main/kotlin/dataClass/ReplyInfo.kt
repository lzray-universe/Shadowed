package moe.tachyon.shadowed.dataClass

import kotlinx.serialization.Serializable

/**
 * Information about a message that is being replied to
 */
@Serializable
data class ReplyInfo(
    val messageId: Long,
    val content: String,
    val senderId: UserId,
    val senderName: String,
    val type: MessageType
)
