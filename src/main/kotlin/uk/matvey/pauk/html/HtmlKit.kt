package uk.matvey.pauk.html

import kotlinx.html.HEAD
import kotlinx.html.LinkRel
import kotlinx.html.LinkType
import kotlinx.html.link

object HtmlKit {

    fun HEAD.stylesheet(url: String) = link(rel = LinkRel.stylesheet, type = LinkType.textCss, href = url)
}