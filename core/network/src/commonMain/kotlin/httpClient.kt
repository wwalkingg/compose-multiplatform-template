import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

private val token = ""

val httpClient = HttpClient(CIO) {
    defaultRequest {
        url("http://lnmath.buzz:9021")
        headers {
            append("Authorization", "Bearer ${token}")
        }
    }
    install(ContentNegotiation) {
        json()
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 15000
    }
    install(Logging)
//    install(HttpRedirect) {
//        checkHttpMethod = false
//    }
}