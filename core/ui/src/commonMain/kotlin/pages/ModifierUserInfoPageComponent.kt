package pages

import ModelState
import UserInfo
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import httpClient
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import state.UserInfoLoadState
import utils.error
import utils.success

class ModifierUserInfoPageComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { ModifierUserInfoModelState() }
}

class ModifierUserInfoModelState : ModelState() {
    private val _userInfoLoadState = MutableStateFlow<UserInfoLoadState>(UserInfoLoadState.Loading)
    val userInfoLoadState = _userInfoLoadState.asStateFlow()

    init {
        loadUserInfo()
    }

    fun loadUserInfo() {
        coroutineScope.launch {
            _userInfoLoadState.emit(UserInfoLoadState.Loading)
            httpClient.get("/filter/getUserById").success<UserInfo> {
                _userInfoLoadState.emit(UserInfoLoadState.Success(it))
            }.error {
                _userInfoLoadState.emit(UserInfoLoadState.Error)
            }
        }
    }
}