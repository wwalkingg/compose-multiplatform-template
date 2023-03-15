package buzz.lnmath.async_image

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import javax.imageio.ImageIO

actual fun byteArrayToImageBitmap(byteArray: ByteArray) = ImageIO.read(byteArray.inputStream()).toComposeImageBitmap()