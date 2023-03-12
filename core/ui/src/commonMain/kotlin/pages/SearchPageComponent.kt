package pages

import FilterSearchTypes
import ModelState
import SearchResult
import SearchState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.collections.immutable.toPersistentSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchPageComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { SearchPageModelState() }
}

class SearchPageModelState : ModelState() {
    var searchValue by mutableStateOf("")
    var selectedTypes by mutableStateOf(emptySet<FilterSearchTypes>().toPersistentSet())
    private val _searchState = MutableStateFlow<SearchState>(SearchState.Success(SearchResult.empty))
    val searchState = _searchState.asStateFlow()
    fun search() {

    }
}