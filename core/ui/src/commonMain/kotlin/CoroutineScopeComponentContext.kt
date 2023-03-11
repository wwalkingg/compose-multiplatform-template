import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel


private val ComponentContext.mCoroutineScope: CoroutineScope by lazy {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    return@lazy scope
}
val ComponentContext.coroutineScope: CoroutineScope
    get() {
        lifecycle.doOnDestroy { mCoroutineScope.cancel() }
        return mCoroutineScope
    }
