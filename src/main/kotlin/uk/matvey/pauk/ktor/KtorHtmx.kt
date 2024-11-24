package uk.matvey.pauk.ktor

import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.header
import io.ktor.server.response.header
import kotlinx.html.HEAD
import kotlinx.html.HtmlBlockTag
import kotlinx.html.ScriptCrossorigin
import kotlinx.html.script
import uk.matvey.pauk.htmx.Htmx
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_BOOST
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_CONFIRM
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_DELETE
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_EXT
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_GET
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_INDICATOR
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_PATCH
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_POST
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_PUSH_URL
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_PUT
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_SWAP
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_SWAP_OOB
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_TARGET
import uk.matvey.pauk.htmx.Htmx.Attribute.HX_TRIGGER
import uk.matvey.pauk.htmx.Htmx.INTEGRITY
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

    fun HtmlBlockTag.hxGet(
        path: String,
        target: String? = null,
        swap: String? = null,
    ) {
        attributes[HX_GET] = path
        target?.let { hxTarget(it) }
        swap?.let { hxSwap(it) }
    }

    fun HtmlBlockTag.hxPost(
        path: String,
        target: String? = null,
        swap: String? = null,
    ) {
        attributes[HX_POST] = path
        target?.let { hxTarget(it) }
        swap?.let { hxSwap(it) }
    }

    fun HtmlBlockTag.hxPut(
        path: String,
        target: String? = null,
        swap: String? = null,
    ) {
        attributes[HX_PUT] = path
        target?.let { hxTarget(it) }
        swap?.let { hxSwap(it) }
    }

    fun HtmlBlockTag.hxPatch(
        path: String,
        target: String? = null,
        swap: String? = null,
    ) {
        attributes[HX_PATCH] = path
        target?.let { hxTarget(it) }
        swap?.let { hxSwap(it) }
    }

    fun HtmlBlockTag.hxDelete(
        path: String,
        target: String? = null,
        swap: String? = null,
    ) {
        attributes[HX_DELETE] = path
        target?.let { hxTarget(it) }
        swap?.let { hxSwap(it) }
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

    fun HtmlBlockTag.hxPushUrl(pushUrl: Boolean = true) {
        attributes[HX_PUSH_URL] = pushUrl.toString()
    }

    fun ApplicationCall.isHxRequest() = request.header(Htmx.RequestHeader.HX_REQUEST) == "true"

    fun ApplicationCall.setHxLocation(location: String) = response.header(Htmx.ResponseHeader.HX_LOCATION, location)

    fun ApplicationCall.setHxRedirect(location: String) = response.header(Htmx.ResponseHeader.HX_REDIRECT, location)
}
