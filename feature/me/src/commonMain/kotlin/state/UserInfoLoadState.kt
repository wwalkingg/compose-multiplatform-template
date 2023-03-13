package state

import UserInfo

sealed interface UserInfoLoadState {
    object Loading : UserInfoLoadState
    object Error : UserInfoLoadState
    data class Success(val userInfo: UserInfo) : UserInfoLoadState
}