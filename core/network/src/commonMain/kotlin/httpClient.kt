import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val token = ""

object KtorHttpClient {
    val client = HttpClient(CIO) {
        defaultRequest {
            host = "http://lnmath.buzz:9021"
            headers{
                append("Authorization","Bearer ${token}")
            }
        }
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(HttpRedirect) {
            checkHttpMethod = false
        }
    }

    suspend inline fun get(url: String): HttpResponse {
        return withContext(Dispatchers.IO) {
            client.get(url)
        }
    }

    @OptIn(InternalAPI::class)
    suspend inline fun  post(url: String, body: Any? = null): HttpResponse {
        return withContext(Dispatchers.IO) {
            client.post(url) {
                contentType(ContentType.Application.Json)
                body?.let { this.body = it }
            }
        }
    }
}