# Pauk

Kotlin web utilities.

## HtmlKit

### Head

* `viewport`
* `stylesheet`

```kotlin
head {
    viewport {
        width = "device-width"
        initialScale = 1.0
    }
    stylesheet("/style.css")
}
```

## Htmx

* Htmx script URL: `Htmx.scriptUrl()`
* Htmx attributes
* Htmx request and response headers
* `hx-swap` options

### SSE

* SSE extension script URL: `Htmx.Sse.scriptUrl()`
* SSE attributes.

## `Resource` interface

Define your resource:

```kotlin
class HealthResource : Resource {
    
    override fun Route.routing() {
        get("/health") {
            call.respondText("OK")
        }
    }
}
```

Register your resource in a Ktor module:

```kotlin
fun Application.module() {
    val healthResource = HealthResource()
    routing {
        with(healthResource) { routing() }
    }
}
```