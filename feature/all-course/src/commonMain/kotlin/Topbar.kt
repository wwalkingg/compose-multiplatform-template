import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDOIT(
    modifier: Modifier = Modifier,
    sortMethod: SortMethod,
    onSortMethodChange: (SortMethod) -> Unit,
    onBackClick: () -> Unit
) {
    LargeTopAppBar(
        modifier = modifier,
        title = {
            Text("全部课程")
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            var isSortMethodDropdownMenuExpanded by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.clip(MaterialTheme.shapes.medium)
                    .clickable { isSortMethodDropdownMenuExpanded = !isSortMethodDropdownMenuExpanded }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    sortMethod.string
                )
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = isSortMethodDropdownMenuExpanded,
                onDismissRequest = { isSortMethodDropdownMenuExpanded = false }) {
                SortMethod.values().forEach {
                    DropdownMenuItem(text = { Text(it.string) }, onClick = {
                        onSortMethodChange(it)
                        isSortMethodDropdownMenuExpanded = false
                    })
                }
            }
        }
    )
}
