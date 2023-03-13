import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.filechooser.FileFilter

actual class ImagePickerStateImpl : ImagePickerState {
    actual var painter by mutableStateOf<Painter?>(null)
        private set

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
        val result = fileChooser.showOpenDialog(frame)
        if (result == JFileChooser.APPROVE_OPTION) {
            // 用户选择了一个文件
            val selectedFile = fileChooser.selectedFile
            // 在这里处理选择的文件
            println("Selected file: " + selectedFile.absolutePath)
            val image = ImageIO.read(selectedFile).toComposeImageBitmap()
            painter = BitmapPainter(image)
        }

        frame.pack()
        frame.isVisible = true
    }
}

@Composable
actual fun rememberImagePickerState(): ImagePickerState {
    return remember { ImagePickerStateImpl() }
}