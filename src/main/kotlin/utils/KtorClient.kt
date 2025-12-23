package moe.tachyon.shadowed.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import moe.tachyon.shadowed.contentNegotiationJson

val ktorClientEngineFactory = CIO

val httpClient = HttpClient(ktorClientEngineFactory)
{
    engine()
    {
        dispatcher = Dispatchers.IO
        requestTimeout = 10_000
    }
    install(ContentNegotiation)
    {
        json(contentNegotiationJson)
    }
}