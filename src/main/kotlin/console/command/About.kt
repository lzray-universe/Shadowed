package moe.tachyon.shadowed.console.command

import kotlinx.serialization.Serializable
import moe.tachyon.shadowed.console.AnsiEffect
import moe.tachyon.shadowed.console.SimpleAnsiColor
import moe.tachyon.shadowed.Loader
import moe.tachyon.shadowed.logger.ShadowedLogger
import moe.tachyon.shadowed.version

/**
 * About command.
 * print some info about this server.
 */
object About: Command
{
    override val description = "Show about."
    override val aliases = listOf("version", "ver")
    val author = Author(
        "CyanTachyon",
        "cyan@tachyon.moe",
        "https://www.tachyon.moe/",
        "https://github.com/CyanTachyon/Shadowed",
    )

    override suspend fun execute(sender: CommandSender, args: List<String>): Boolean
    {
        Loader.getResource(Loader.CYAN_LOGO)
            ?.bufferedReader()
            ?.lines()
            ?.toList()
            ?.forEach { sender.out("${SimpleAnsiColor.CYAN}${AnsiEffect.BOLD}$it") }
        ?: ShadowedLogger.getLogger().severe("${Loader.CYAN_LOGO} not found")

        sender.out("|> Version: $version")
        sender.out("|> Author: ${author.name}")
        sender.out("|> Github: ${author.github}")
        sender.out("|> Website: ${author.website}")
        sender.out("|> Email: ${author.email}")
        return true
    }

    @Serializable
    data class Author(
        val name: String,
        val email: String,
        val website: String,
        val github: String,
    )
    {
        companion object
        {
            val example = Author("Author", "email@email.com", "https://example.com", "https://github.com")
        }
    }
}