package buzz.lnmath.async_image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class AsyncImageState internal constructor() {
    private val _loadAsyncImageState = MutableStateFlow<LoadAsyncImageState>(LoadAsyncImageState.Loading)
    val loadAsyncImageState = _loadAsyncImageState.asStateFlow()

    suspend fun loadNetImage(url: String) = withContext(Dispatchers.IO) {
        _loadAsyncImageState.emit(LoadAsyncImageState.Loading)
        httpClient.get(url).let {
            if (it.status.isSuccess()) {
                _loadAsyncImageState.emit(LoadAsyncImageState.Success(it.bodyAsChannel().toByteArray()))
            } else {
                _loadAsyncImageState.emit(LoadAsyncImageState.Fail)
            }
        }
    }

}

@Composable
fun rememberAsyncImageState(url: String): AsyncImageState {
    val state = remember { AsyncImageState() }
    LaunchedEffect(url) {
        state.loadNetImage(url)
    }
    return state
}

sealed interface LoadAsyncImageState {
    object Loading : LoadAsyncImageState
    object Fail : LoadAsyncImageState
    data class Success(val byteArray: ByteArray) : LoadAsyncImageState {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Success

            if (!byteArray.contentEquals(other.byteArray)) return false

            return true
        }

        override fun hashCode(): Int {
            return byteArray.contentHashCode()
        }
    }
}