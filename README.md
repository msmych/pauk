# Pauk

Kotlin web utilities.

## HtmlKit

Include stylesheet in head:

```kotlin
head {
    stylesheet("/style.css")
}
```

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