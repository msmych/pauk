package uk.matvey.pauk.ktor

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.server.application.ApplicationCall
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.EngineSSLConnectorBuilder
import io.ktor.server.engine.sslConnector
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondText
import io.ktor.server.util.getOrFail
import kotlinx.css.CssBuilder
import java.io.File
import java.io.FileInputStream
import java.security.KeyStore

object KtorKit {

    fun ApplicationCall.pathParam(name: String) = parameters.getOrFail(name)

    fun ApplicationCall.pathParamOrNull(name: String) = parameters[name]

    fun ApplicationCall.queryParamOrNull(name: String) = request.queryParameters[name]

    fun ApplicationCall.queryParam(name: String) = request.queryParameters.getOrFail(name)

    fun ApplicationCall.queryParams(name: String) = request.queryParameters.entries()
        .find { (k, _) -> k == name }
        ?.value
        ?: listOf()

    suspend fun ApplicationCall.receiveParamsMap() = receiveParameters().entries()
        .associate { (k, v) -> k to v.joinToString(";") }

    suspend fun ApplicationCall.respondCss(block: CssBuilder.() -> Unit) {
        respondText(CssBuilder().apply(block).toString(), ContentType.Text.CSS)
    }

    fun ApplicationEngine.Configuration.configureSsl(
        privateKeyPassword: String,
        keyStoreFilePath: String,
        keyStorePassword: String,
        keyAlias: String,
        builder: EngineSSLConnectorBuilder.() -> Unit = {},
    ) {
        val privateKeyPasswordChars = privateKeyPassword.toCharArray()
        val keyStoreFile = File(keyStoreFilePath)
        sslConnector(
            keyStore = KeyStore.getInstance("JKS").apply {
                load(FileInputStream(keyStoreFile), privateKeyPasswordChars)
            },
            keyAlias = keyAlias,
            privateKeyPassword = { privateKeyPasswordChars },
            keyStorePassword = { keyStorePassword.toCharArray() },
        ) {
            keyStorePath = keyStoreFile
            builder()
        }
    }

    fun HttpRequestBuilder.setFormData(params: Map<String, String>) {
        setBody(
            FormDataContent(
                Parameters.build {
                    params.forEach { (k, v) -> append(k, v) }
                }
            )
        )
    }

    fun HttpRequestBuilder.setFormData(vararg params: Pair<String, String>) = setFormData(params.toMap())
}
