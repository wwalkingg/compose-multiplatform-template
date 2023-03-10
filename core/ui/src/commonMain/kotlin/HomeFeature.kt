import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeFeature(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        val sizeModifier =
            if (this.maxWidth > 600.dp) Modifier.width(400.dp).fillMaxHeight() else Modifier.fillMaxSize()
                .animateContentSize()
        Column {
            HomeScreen(sizeModifier.animateContentSize())
            if (this@BoxWithConstraints.maxWidth > 600.dp) {
                Column(Modifier.weight(1f).background(MaterialTheme.colorScheme.primary)) {
                    Text("HAHAHAHA")
                }
            }
        }
    }
}