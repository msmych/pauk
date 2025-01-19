package uk.matvey.pauk.html

import kotlinx.html.HEAD
import kotlinx.html.LinkRel
import kotlinx.html.LinkType
import kotlinx.html.link
import kotlinx.html.meta

object HtmlKit {

    /**
     * [Viewport meta tag](https://developer.mozilla.org/en-US/docs/Web/HTML/Viewport_meta_tag)
     *
     * Defaults are `width=device-width, initial-scale=1`
     */
    class Viewport {

        var width: String? = "device-width"
        var height: String? = null
        var initialScale: Double? = 1.0
        var minimumScale: Double? = null
        var maximumScale: Double? = null
        var userScalable: Boolean? = null
        var interactiveWidget: InteractiveWidget? = null

        init {
            initialScale?.let {
                require(it in 0.1..10.0) { "initialScale must be in range [0.1, 10.0]" }
            }
            minimumScale?.let {
                require(it in 0.1..10.0) { "minimumScale must be in range [0.1, 10.0]" }
            }
            maximumScale?.let {
                require(it in 0.1..10.0) { "maximumScale must be in range [0.1, 10.0]" }
            }
        }

        enum class InteractiveWidget(val value: String) {
            RESIZES_VISUAL("resizes-visual"),
            RESIZES_CONTENT("resizes-content"),
            OVERLAYS_CONTENT("overlays-content");
        }

        companion object {
            fun HEAD.viewport(block: Viewport.() -> Unit = {}) = Viewport().apply(block).let { viewport ->
                val values = listOfNotNull(
                    viewport.width?.let { "width=$it" },
                    viewport.height?.let { "height=$it" },
                    viewport.initialScale.let { "initial-scale=$it" },
                    viewport.minimumScale?.let { "minimum-scale=$it" },
                    viewport.maximumScale?.let { "maximum-scale=$it" },
                    viewport.userScalable?.let { "user-scalable=${if (it) "1" else "0"}" },
                    viewport.interactiveWidget?.let { "interactive-widget=${it.value}" }
                )
                meta {
                    name = "viewport"
                    content = values.joinToString(", ")
                }
            }
        }
    }

    fun HEAD.stylesheet(href: String) = link(rel = LinkRel.stylesheet, type = LinkType.textCss, href = href)
}