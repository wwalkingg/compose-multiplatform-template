sealed interface LoginResultState {
    object None : LoginResultState
    object Loading : LoginResultState
    object Error : LoginResultState
    data class Success(val data: LoginResp) : LoginResultState
}