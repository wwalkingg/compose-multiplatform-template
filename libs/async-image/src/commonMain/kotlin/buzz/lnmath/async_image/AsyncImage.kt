package buzz.lnmath.async_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    val state = rememberAsyncImageState(url)
    val loadAsyncImageState by state.loadAsyncImageState.collectAsState()
    when (loadAsyncImageState) {
        LoadAsyncImageState.Fail -> {
            Box(modifier.background(MaterialTheme.colorScheme.errorContainer))
        }

        LoadAsyncImageState.Loading -> {
            Box(modifier.shimmerBackground())
        }

        is LoadAsyncImageState.Success -> {
            Image(
                byteArrayToImageBitmap((loadAsyncImageState as LoadAsyncImageState.Success).byteArray),
                contentDescription,
                modifier,
                alignment,
                contentScale,
                alpha,
                colorFilter
            )
        }
    }

}

expect fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap
