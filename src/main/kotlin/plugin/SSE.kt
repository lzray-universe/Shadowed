@file:Suppress("PackageDirectoryMismatch")

package moe.tachyon.shadowed.plugin.sse

import io.ktor.server.application.*
import io.ktor.server.sse.*

fun Application.installSSE() = install(SSE)