import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchState: SearchState,
    searchValue: String,
    selectedTypes: Set<FilterSearchTypes>,
    onErrorReload: () -> Unit,
    onSelectedTypesChange: (Set<FilterSearchTypes>) -> Unit,
    onSearchValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    Column(modifier.padding(horizontal = 10.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchValue,
            onValueChange = onSearchValueChange,
            trailingIcon = {
                IconButton(onClick = onSearchClick) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            },
            placeholder = { Text("请输入关键字") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedBorderColor = Color.Unspecified,
            )
        )
        FilterChips(selectedTypes, onSelectedTypesChange)
        when (searchState) {
            SearchState.Error -> ErrorPage(onRefreshClick = onErrorReload)
            SearchState.Loading -> {
                LoadingPage()
            }

            is SearchState.Success -> {
                Success(modifier = Modifier.fillMaxSize(), searchResult = searchState.data)
            }
        }
    }
}

