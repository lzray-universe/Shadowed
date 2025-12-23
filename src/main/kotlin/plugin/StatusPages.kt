@file:Suppress("PackageDirectoryMismatch")

package moe.tachyon.shadowed.plugin.statusPages

import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import moe.tachyon.shadowed.logger.ShadowedLogger

/**
 * 对于不同的状态码返回不同的页面
 */
fun Application.installStatusPages() = install(StatusPages)
{
    val logger = ShadowedLogger.getLogger()
    exception<Throwable>
    { call, throwable ->
        if (call.response.status() == null) logger.warning("出现位置错误, 访问接口: ${call.request.path()}", throwable)
        else logger.config("抛出错误，但状态码已设置, 访问接口: ${call.request.path()}", throwable)
    }
}