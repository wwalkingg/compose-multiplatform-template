import com.russhwolf.settings.get
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.ProxyBuilder.http
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import utils.ResponseTestWrapper

private val token = settings.getStringOrNull("token") ?: ""

private val json = Json { ignoreUnknownKeys = true }
val baseUrl = "http://172.18.5.33:8082/"
@OptIn(InternalAPI::class)
val httpClient = HttpClient(CIO) {
    defaultRequest {
//        url("http://lnmath.buzz:9021")
        url(baseUrl)
        headers {
            append("Authorization", token)
        }
    }
    install(ContentNegotiation) {
        json(json = Json {
            ignoreUnknownKeys = true
        })
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 15000
    }
    install(Logging)
//    HttpResponseValidator {
//        validateResponse { response ->
//            if (response.status.value in 200..299) {
//                val jsonElement = json.parseToJsonElement(response.content.toString())
//                if (!jsonElement.jsonObject.keys.containsAll(listOf("code", "msg"))){
//                    throw ResponseValideException("返回统一格式错误")
//                }
//            }
//        }
//    }

//    install(HttpRedirect) {
//        checkHttpMethod = false
//    }
}