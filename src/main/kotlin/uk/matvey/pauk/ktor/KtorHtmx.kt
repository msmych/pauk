package uk.matvey.pauk.ktor

import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.header
import io.ktor.server.response.header
import kotlinx.html.HEAD
import kotlinx.html.HtmlBlockTag
import kotlinx.html.ScriptCrossorigin
import kotlinx.html.script
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.buildJsonObject
import uk.matvey.kit.json.JsonKit.JSON
import uk.matvey.pauk.htmx.Htmx
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_BOOST
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_CONFIRM
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_DELETE
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_EXT
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_GET
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_INCLUDE
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_INDICATOR
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_PATCH
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_POST
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_PUSH_URL
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_PUT
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_SWAP
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_SWAP_OOB
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_TARGET
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_TRIGGER
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_VALS
import uk.matvey.pauk.htmx.Htmx.INTEGRITY
import uk.matvey.pauk.htmx.Htmx.RequestHeader
import uk.matvey.pauk.htmx.Htmx.ResponseHeader
import uk.matvey.pauk.htmx.Htmx.Sse.SSE_CONNECT
import uk.matvey.pauk.htmx.Htmx.Sse.SSE_SWAP
import uk.matvey.pauk.htmx.Htmx.VERSION

object KtorHtmx {

    fun HEAD.htmxScript(version: String = VERSION, integrity: String = INTEGRITY) = script {
        this.src = Htmx.scriptUrl(version)
        this.integrity = integrity
        this.crossorigin = ScriptCrossorigin.anonymous
    }

    fun HEAD.sseExtScript(version: String = VERSION) = script {
        src = Htmx.Sse.scriptUrl(version)
    }

    fun HtmlBlockTag.hxGet(resource: String) {
        attributes[HX_GET] = resource
    }

    fun HtmlBlockTag.hxPost(resource: String) {
        attributes[HX_POST] = resource
    }

    fun HtmlBlockTag.hxPut(resource: String) {
        attributes[HX_PUT] = resource
    }

    fun HtmlBlockTag.hxPatch(resource: String) {
        attributes[HX_PATCH] = resource
    }

    fun HtmlBlockTag.hxDelete(resource: String) {
        attributes[HX_DELETE] = resource
    }

    fun HtmlBlockTag.hxSse(
        connect: String,
        swap: String,
    ) {
        attributes[HX_EXT] = Htmx.Sse.SSE
        attributes[SSE_CONNECT] = connect
        attributes[SSE_SWAP] = swap
    }

    fun HtmlBlockTag.hxTarget(target: String) {
        attributes[HX_TARGET] = target
    }

    fun HtmlBlockTag.hxSwap(swap: String) {
        attributes[HX_SWAP] = swap
    }

    fun HtmlBlockTag.hxSwapOob() {
        attributes[HX_SWAP_OOB] = "true"
    }

    fun HtmlBlockTag.hxTrigger(trigger: String) {
        attributes[HX_TRIGGER] = trigger
    }

    fun HtmlBlockTag.hxBoost(boost: Boolean = true) {
        attributes[HX_BOOST] = boost.toString()
    }

    fun HtmlBlockTag.hxConfirm(message: String) {
        attributes[HX_CONFIRM] = message
    }

    fun HtmlBlockTag.hxIndicator(indicator: String) {
        attributes[HX_INDICATOR] = indicator
    }

    fun HtmlBlockTag.hxInclude(include: String) {
        attributes[HX_INCLUDE] = include
    }

    fun HtmlBlockTag.hxVals(vals: String) {
        attributes[HX_VALS] = vals
    }

    fun HtmlBlockTag.hxVals(block: JsonObjectBuilder.() -> Unit) {
        hxVals(JSON.encodeToString(buildJsonObject(block)))
    }

    fun HtmlBlockTag.hxPushUrl(pushUrl: Boolean = true) {
        attributes[HX_PUSH_URL] = pushUrl.toString()
    }

    fun ApplicationCall.isHxRequest() = request.header(RequestHeader.HX_REQUEST) == "true"

    fun ApplicationCall.setHxLocation(location: String) = response.header(ResponseHeader.HX_LOCATION, location)

    fun ApplicationCall.setHxRedirect(location: String) = response.header(ResponseHeader.HX_REDIRECT, location)
}
