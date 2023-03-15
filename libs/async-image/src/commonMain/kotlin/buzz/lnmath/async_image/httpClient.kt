package buzz.lnmath.async_image

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.cache.*

internal val httpClient = HttpClient(CIO) {
    install(HttpCache)
}