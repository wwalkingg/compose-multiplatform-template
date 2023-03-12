import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import pages.LoginPageComponent
import pages.MainComponent

internal val navigation = StackNavigation<RootComponent.Config>()

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val _childStack =
        childStack(
            source = navigation,
            initialConfiguration = Config.MainConfig,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    val stack: Value<ChildStack<*, Child>> = _childStack

    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.MainConfig -> Child.MainPage(MainComponent(componentContext))
            Config.LoginConfig -> Child.LoginPage(LoginPageComponent(componentContext))
        }

    sealed class Config : Parcelable {
        @Parcelize
        object MainConfig : Config()

        @Parcelize
        object LoginConfig : Config()
    }

    sealed class Child {
        data class MainPage(val component: MainComponent) : Child()
        data class LoginPage(val component: LoginPageComponent) : Child()
    }
}
