import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.subscribe
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import pages.*

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
            Config.AllCourseConfig -> Child.AllCoursePage(AllCoursePageComponent(componentContext))
            Config.FindPartnerConfig -> Child.FindPartnerPage(FindPartnerPageComponent(componentContext))
            Config.PersonalHealthConfig -> Child.PersonalHealthPage(PersonalHealthPageComponent(componentContext))
            Config.TODOConfig -> Child.TODOPage(TODOPageComponent(componentContext))
            is Config.CourseDetailConfig -> Child.CourseDetailPage(
                CourseDetailPageComponent(
                    componentContext = componentContext,
                    id = config.id
                )
            )

            Config.SearchConfig -> Child.SearchPage(SearchPageComponent(componentContext))
            Config.ModifierPassword -> Child.ModifierPasswordPage(ModifierPasswordPageComponent(componentContext))
            Config.ModifierUserInfo -> Child.ModifierUserInfoPage(ModifierUserInfoPageComponent(componentContext))
        }

    sealed class Config : Parcelable {
        @Parcelize
        object MainConfig : Config()

        @Parcelize
        object LoginConfig : Config()

        @Parcelize
        object AllCourseConfig : Config()

        @Parcelize
        object PersonalHealthConfig : Config()

        @Parcelize
        object TODOConfig : Config()

        @Parcelize
        object FindPartnerConfig : Config()

        @Parcelize
        data class CourseDetailConfig(val id: Int) : Config()

        @Parcelize
        object SearchConfig : Config()

        @Parcelize
        object ModifierUserInfo : Config()

        @Parcelize
        object ModifierPassword : Config()
    }

    sealed class Child {
        data class MainPage(val component: MainComponent) : Child()
        data class LoginPage(val component: LoginPageComponent) : Child()
        data class AllCoursePage(val component: AllCoursePageComponent) : Child()
        data class PersonalHealthPage(val component: PersonalHealthPageComponent) : Child()
        data class TODOPage(val component: TODOPageComponent) : Child()
        data class FindPartnerPage(val component: FindPartnerPageComponent) : Child()
        data class CourseDetailPage(val component: CourseDetailPageComponent) : Child()
        data class SearchPage(val component: SearchPageComponent) : Child()
        data class ModifierUserInfoPage(val component: ModifierUserInfoPageComponent) : Child()
        data class ModifierPasswordPage(val component: ModifierPasswordPageComponent) : Child()
    }
}
