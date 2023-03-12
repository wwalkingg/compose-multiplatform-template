import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.essenty.instancekeeper.InstanceKeeper

@Stable
class LoginScreenState : InstanceKeeper.Instance {
    var id by mutableStateOf("123456")
    var password by mutableStateOf("147258")

    override fun onDestroy() {

    }
}