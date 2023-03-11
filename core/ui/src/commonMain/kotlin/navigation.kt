import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import pages.HomePageComponent
import pages.LoginPageComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _childStack =
        childStack(
            source = navigation,
            initialConfiguration = Config.LoginConfig,
            handleBackButton = true, // Pop the back stack on back button press
            childFactory = ::createChild,
        )

    val stack: Value<ChildStack<*, Child>> = _childStack

    private fun createChild(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            Config.HomeConfig -> Child.HomePage(HomePageComponent(componentContext))
            Config.LoginConfig -> Child.LoginPage(LoginPageComponent(componentContext, navigation))
        }

    sealed class Config : Parcelable {
        @Parcelize
        object HomeConfig : Config()

        @Parcelize
        object LoginConfig : Config()
    }

    sealed class Child {
        data class HomePage(val component: HomePageComponent) : Child()
        data class LoginPage(val component: LoginPageComponent) : Child()
    }
}
