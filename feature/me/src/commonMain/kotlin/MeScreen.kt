import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import state.UserInfoLoadState

@Composable
fun MeScreen(
    modifier: Modifier = Modifier,
    userInfoLoadState: UserInfoLoadState,
    onCollectClick: () -> Unit,
    onModifierUserInfoClick: () -> Unit,
    onModifierPasswordClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(modifier = modifier) {
        UserInfo(
            modifier = Modifier.fillMaxWidth().padding(10.dp, 20.dp),
            userInfoLoadState
        )
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant)) {
            Spacer(Modifier.height(10.dp))
            SettingItem(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("收藏") },
                icon = { Icon(Icons.Default.Info, contentDescription = null) },
                onClick = onCollectClick
            )
            Divider(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(start = 44.dp))
            SettingItem(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("修改用户信息") },
                icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                onClick = onModifierUserInfoClick
            )
            Divider(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(start = 44.dp))
            SettingItem(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("修改密码") },
                onClick = onModifierPasswordClick
            )
            Divider(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(start = 44.dp))
            SettingItem(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("退出登录") },
                onClick = onLogoutClick
            )
        }
    }
}



