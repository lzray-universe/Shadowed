package moe.tachyon.shadowed

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val contentNegotiationJson = Json()
{
    encodeDefaults = true
    prettyPrint = debug
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = true
    allowSpecialFloatingPointValues = true
    decodeEnumsCaseInsensitive = true
    allowTrailingComma = true
    classDiscriminatorMode = ClassDiscriminatorMode.NONE
    allowComments = true
}

/**
 * 用作数据处理的json序列化/反序列化
 */
val dataJson = Json(contentNegotiationJson)
{
    prettyPrint = false
    encodeDefaults = false
}

/**
 * 用作api文档等展示的json序列化/反序列化
 */
val showJson = Json(contentNegotiationJson)
{
    prettyPrint = true
}