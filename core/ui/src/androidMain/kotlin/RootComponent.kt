import com.arkivanov.decompose.ComponentContext

actual class RootComponent actual constructor(componentContext: ComponentContext) :
    ComponentContext by componentContext {}