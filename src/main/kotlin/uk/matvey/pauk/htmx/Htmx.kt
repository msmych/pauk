package uk.matvey.pauk.htmx

/**
 * [HTMX](https://htmx.org)
 */
object Htmx {

    const val VERSION = "2.0.3"
    const val INTEGRITY = "sha384-0895/pl2MU10Hqc6jd4RvrthNlDiE9U1tWmX7WRESftEDRosgxNsQG/Ze9YMRzHq"

    fun scriptUrl(version: String = VERSION) = "https://unpkg.com/htmx.org@$version"

    /**
     * [Core Attributes](https://htmx.org/reference/#attributes)
     *
     * [Additional Attributes](https://htmx.org/reference/#attributes-additional)
     */
    object Attribute {

        /**
         * [hx-get](https://htmx.org/attributes/hx-get/)
         */
        const val HX_GET = "hx-get"

        /**
         * [hx-post](https://htmx.org/attributes/hx-post/)
         */
        const val HX_POST = "hx-post"

        /**
         * [hx-put](https://htmx.org/attributes/hx-put/)
         */
        const val HX_PUT = "hx-put"

        /**
         * [hx-patch](https://htmx.org/attributes/hx-patch/)
         */
        const val HX_PATCH = "hx-patch"

        /**
         * [hx-delete](https://htmx.org/attributes/hx-delete/)
         */
        const val HX_DELETE = "hx-delete"

        /**
         * [hx-target](https://htmx.org/attributes/hx-target/)
         */
        const val HX_TARGET = "hx-target"

        /**
         * [hx-swap](https://htmx.org/attributes/hx-swap/)
         */
        const val HX_SWAP = "hx-swap"

        /**
         * [hx-trigger](https://htmx.org/attributes/hx-trigger/)
         */
        const val HX_TRIGGER = "hx-trigger"

        /**
         * [hx-indicator](https://htmx.org/attributes/hx-indicator/)
         */
        const val HX_INDICATOR = "hx-indicator"

        /**
         * [hx-ext](https://htmx.org/attributes/hx-ext/)
         */
        const val HX_EXT = "hx-ext"

        /**
         * [hx-push-url](https://htmx.org/attributes/hx-push-url/)
         */
        const val HX_PUSH_URL = "hx-push-url"
    }

    /**
     * [Request Headers](https://htmx.org/reference/#request_headers)
     */
    object RequestHeader {
        const val HX_REQUEST = "HX-Request"
    }

    /**
     * [Response Headers](https://htmx.org/reference/#response_headers)
     */
    object ResponseHeader {
        /**
         * [HX-Location](https://htmx.org/headers/hx-location/)
         */
        const val HX_LOCATION = "HX-Location"

        /**
         * [HX-Redirect](https://htmx.org/headers/hx-redirect/)
         */
        const val HX_REDIRECT = "HX-Redirect"
    }

    /**
     * [SSE](https://htmx.org/extensions/sse/)
     */
    object Sse {
        const val VERSION = "2.2.2"

        fun scriptUrl(version: String = VERSION) = "https://unpkg.com/htmx-ext-sse@$version"

        const val SSE = "sse"
        const val SSE_CONNECT = "sse-connect"
        const val SSE_SWAP = "sse-swap"
    }
}