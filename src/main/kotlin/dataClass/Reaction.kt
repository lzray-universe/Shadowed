package moe.tachyon.shadowed.dataClass

import kotlinx.serialization.Serializable

/**
 * Represents an emoji reaction on a message
 * @property emoji The emoji character
 * @property userIds List of user IDs who reacted with this emoji
 */
@Serializable
data class Reaction(
    val emoji: String,
    val userIds: List<UserId>
)
