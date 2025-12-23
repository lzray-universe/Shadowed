package moe.tachyon.shadowed.utils

import moe.tachyon.shadowed.console.AnsiStyle.Companion.RESET
import moe.tachyon.shadowed.console.SimpleAnsiColor.Companion.CYAN
import moe.tachyon.shadowed.console.SimpleAnsiColor.Companion.PURPLE
import moe.tachyon.shadowed.logger.ShadowedLogger
import java.io.File
import kotlin.system.exitProcess

@Suppress("unused")
object Power
{
    @JvmField
    val logger = ShadowedLogger.getLogger()
    private var isShutdown: Int? = null

    @JvmStatic
    fun shutdown(code: Int, cause: String = "unknown"): Nothing
    {
        synchronized(Power)
        {
            if (isShutdown != null) exitProcess(isShutdown!!)
            isShutdown = code
        }
        logger.warning("${PURPLE}Server is shutting down: ${CYAN}$cause${RESET}")
        startShutdownHook(code)
        exitProcess(code)
    }

    @JvmStatic
    private fun startShutdownHook(code: Int)
    {
        val hook = Thread()
        {
            try
            {
                Thread.sleep(3000)
                logger.severe("检测到程序未退出，尝试强制终止")
                Runtime.getRuntime().halt(code)
            }
            catch (e: InterruptedException)
            {
                Thread.currentThread().interrupt()
            }
        }
        hook.isDaemon = true
        hook.start()
    }

    @JvmStatic
    fun init() = runCatching()
    {
        val javaVersion = System.getProperty("java.specification.version")
        if (javaVersion != "17")
        {
            logger.severe("Java version is $javaVersion, but Shadowed requires Java 17.")
            shutdown(1, "Java version is $javaVersion, but Shadowed requires Java 17.")
        }

        val file = File(this.javaClass.protectionDomain.codeSource.location.toURI())
        val lst = file.lastModified()
        val thread = Thread()
        {
            try
            {
                while (true)
                {
                    Thread.sleep(1000)
                    val newLst = file.lastModified()
                    if (newLst != lst)
                    {
                        logger.warning("检测到文件 ${file.name} 已被修改，程序将自动关闭")
                        shutdown(0, "File ${file.name} modified")
                    }
                }
            }
            catch (e: InterruptedException)
            {
                Thread.currentThread().interrupt()
            }
        }
        thread.isDaemon = true
        thread.start()
    }.onFailure()
    {
        logger.severe("启动监视器失败", it)
        shutdown(1, "Failed to start monitoring")
    }
}