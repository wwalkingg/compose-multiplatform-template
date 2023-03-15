package buzz.lnmath.async_image

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap

actual fun byteArrayToImageBitmap(byteArray: ByteArray) =
    BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()