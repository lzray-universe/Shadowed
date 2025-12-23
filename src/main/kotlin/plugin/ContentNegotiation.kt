@file:Suppress("PackageDirectoryMismatch")

package moe.tachyon.shadowed.plugin.contentNegotiation

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import moe.tachyon.shadowed.contentNegotiationJson

/**
 * 安装反序列化/序列化服务(用于处理json)
 */
fun Application.installContentNegotiation() = install(ContentNegotiation)
{
    json(contentNegotiationJson)
}