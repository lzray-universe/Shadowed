package moe.tachyon.shadowed.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class ChatMember(
    val chatId: ChatId,
    val name: String?,
    val key: String,
    val parsedOtherNames: List<String>,
    val parsedOtherIds: List<Int>,
    val isPrivate: Boolean,
    val unreadCount: Int
)
