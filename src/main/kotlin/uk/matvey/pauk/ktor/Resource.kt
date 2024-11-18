package uk.matvey.pauk.ktor

import io.ktor.server.routing.Route

interface Resource {

    fun Route.routing()
}
