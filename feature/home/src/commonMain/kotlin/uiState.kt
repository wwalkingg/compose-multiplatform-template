sealed interface SwiperUIState {
    object Fail : SwiperUIState
    data class Success(
        val list: List<SwiperResp>
    ) : SwiperUIState

    object Loading : SwiperUIState
}

sealed interface RecommendsUIState {
    object Fail : RecommendsUIState
    data class Success(
        val list: List<String>
    ) : RecommendsUIState

    object Loading : RecommendsUIState
}