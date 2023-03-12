package pages

import ModelState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.doOnResume
import com.arkivanov.essenty.lifecycle.doOnStart
import com.arkivanov.essenty.lifecycle.subscribe
import pages.main.*

class MainComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { MainModelState() }

    val l = lifecycle.apply {
        subscribe{
            doOnStart { println("start") }
            doOnDestroy { println("destory") }
            doOnResume { println("re") }
        }
    }
    val home = HomeComponent(childContext("Home",l ))
    val plan = PlanComponent(childContext("Plan", lifecycle))
    val person = PersonComponent(childContext("Person", lifecycle))
    val statistics = StatisticsComponent(childContext("Statistics", lifecycle))
    val me = MeComponent(childContext("Me", lifecycle))

}

@Stable
class MainModelState : ModelState() {
    var menuIndex by mutableStateOf(0)
}