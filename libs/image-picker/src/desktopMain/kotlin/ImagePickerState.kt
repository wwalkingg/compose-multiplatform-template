import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.platform.LocalWindowInfo
import io.ktor.client.content.*
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.filechooser.FileFilter

actual class ImagePickerStateImpl : ImagePickerState {
    private var _inputStream = mutableStateOf<InputStream?>(null)
    private var file: File? = null
    actual override val inputStream get() = _inputStream.value
    actual override val painter: Painter?
        get() {
            if (file == null) return null
            val image = ImageIO.read(file).toComposeImageBitmap()
            return BitmapPainter(image)
        }

    override fun pick() {
        val frame = JFrame("Image Chooser Example")
        val fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
        fileChooser.fileFilter = object : FileFilter() {
            override fun accept(f: File): Boolean {
                val filename: String = f.getName()
                return (f.isDirectory() || filename.endsWith(".gif")
                        || filename.endsWith(".jpg") || filename.endsWith(".png"))
            }

            override fun getDescription(): String {
                return "Image Files (*.gif, *.jpg, *.png)";
            }
        }
        val result = fileChooser.showOpenDialog(JFrame().apply { isVisible = false })
        if (result == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile
            file = selectedFile
            _inputStream.value = selectedFile.inputStream()
        }

        frame.pack()
        frame.isVisible = true
    }
}

@Composable
actual fun rememberImagePickerState(): ImagePickerState {
    return remember { ImagePickerStateImpl() }
}