package org.example.kotlin.multiplatform.data

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.Json
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.utils.EmptyContent
import io.ktor.http.URLProtocol
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.internal.UnitDescriptor
import kotlinx.serialization.json.Json.Companion.nonstrict
import org.example.kotlin.multiplatform.api.Api
import org.example.kotlin.multiplatform.data.model.ApiResult

class NetworkDataSource(networkConfig: NetworkConfig = defaultNetworkConfig) {

    private val httpClient: HttpClient = makeHttpClient(networkConfig)

    suspend fun retrieveUsers(numContacts: Int): ApiResult =
        httpClient.get(path = Api.retrieveRandomUsers(numContacts))
}

@UseExperimental(UnstableDefault::class)
private fun makeHttpClient(
    networkConfig: NetworkConfig
): HttpClient = HttpClient {
    defaultRequest {
        url {
            host = networkConfig.host
            protocol = if (networkConfig.secure) URLProtocol.HTTPS else URLProtocol.HTTP
        }
    }
    Json {
        serializer = KotlinxSerializer(json = nonstrict).apply {
            register(ApiResult.serializer())
            register(EmptyContentSerializer)
        }
    }
    Logging {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}

private object EmptyContentSerializer : KSerializer<EmptyContent> {
    override val descriptor: SerialDescriptor = UnitDescriptor

    override fun deserialize(decoder: Decoder): EmptyContent = EmptyContent.also { decoder.decodeUnit() }

    override fun serialize(encoder: Encoder, obj: EmptyContent) = encoder.encodeUnit()
}

val defaultNetworkConfig = NetworkConfig()
