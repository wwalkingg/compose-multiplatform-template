package pages

import LoginParameter
import LoginResp
import LoginResultState
import ModelState
import RootComponent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.russhwolf.settings.set
import httpClient
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import navigation
import settings
import utils.error
import utils.success

class LoginPageComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { LoginModelState() }
}

class LoginModelState : ModelState() {
    private val _loginResultState = MutableStateFlow<LoginResultState>(LoginResultState.None)
    val loginResultState = _loginResultState.asStateFlow()
    var id by mutableStateOf("123456")
    var password by mutableStateOf("147258")
    fun login() {
        coroutineScope.launch {
            _loginResultState.emit(LoginResultState.Loading)
            httpClient.post("/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginParameter(id, password))
            }.success<LoginResp> {
                settings.set("token", it.token)
//                settings.set("userInfo",)
                _loginResultState.emit(LoginResultState.Success(it))
                navigation.replaceAll(RootComponent.Config.MainConfig)
            }.error {
                _loginResultState.emit(LoginResultState.Error)
                delay(2000L)
                _loginResultState.emit(LoginResultState.None)
            }
        }
    }
}