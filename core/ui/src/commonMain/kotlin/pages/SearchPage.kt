package pages

import SearchScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.pop
import kotlinx.collections.immutable.toPersistentSet
import navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(component: SearchPageComponent) {
    Scaffold(topBar = { TopBar() }) {
        val searchState by component.modelState.searchState.collectAsState()
        SearchScreen(
            modifier = Modifier.padding(it),
            searchState = searchState,
            searchValue = component.modelState.searchValue,
            onSearchValueChange = { component.modelState.searchValue = it },
            onSearchClick = { component.modelState.search() },
            selectedTypes = component.modelState.selectedTypes,
            onErrorReload = {component.modelState.search()},
            onSelectedTypesChange = {
                component.modelState.selectedTypes = it.toPersistentSet()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { navigation.pop() }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        })
}