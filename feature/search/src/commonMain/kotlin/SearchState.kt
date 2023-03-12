sealed interface SearchState {
    object Loading : SearchState
    object Error : SearchState
    data class Success(val data: SearchResult) : SearchState
}