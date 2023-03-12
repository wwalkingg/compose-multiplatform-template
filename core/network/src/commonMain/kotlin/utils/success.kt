package utils

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend inline fun <reified T> HttpResponse.success(callback: HttpResponse.(T) -> Unit): HttpResponse {
    if (status == HttpStatusCode.OK) {
        val response: ResponseTestWrapper = body()
        if (response.code == 200) {
            val real: ResponseWrapper<T> = body()
            this.apply { callback(real.data) }
        }
    }
    return this
}

suspend inline fun HttpResponse.error(callback: HttpResponse.() -> Unit): HttpResponse {
    if (status != HttpStatusCode.OK) {
        this.apply(callback)
        return this
    } else {
        val response: ResponseTestWrapper = body()
        if (response.code != 200) {
            this.apply(callback)
            return this
        }
    }
    return this
}