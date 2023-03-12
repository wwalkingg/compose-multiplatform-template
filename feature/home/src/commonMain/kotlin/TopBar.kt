import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(onSearchClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(50.dp).padding(8.dp)) {
        Image(
            painter = painterResources("logo.png"),
            contentDescription = null,
            modifier = Modifier.align(
                Alignment.Center
            ).fillMaxHeight().padding(4.dp)
        )
        IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                painter = painterResources("magnifying_glass.xml"),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}