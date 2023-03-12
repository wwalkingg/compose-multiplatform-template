import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun UserInfo(
    modifier: Modifier = Modifier,
    userInfoLoadState: UserInfoLoadState,
) {
    when (userInfoLoadState) {
        UserInfoLoadState.Error -> {
            UserInfoLoading()
        }

        UserInfoLoadState.Loading -> {
            UserInfoLoading()
        }

        is UserInfoLoadState.Success -> {
            UserInfoSuccess(userInfoLoadState.userInfo)
        }
    }


}

@Composable
private fun UserInfoSuccess(userInfo: UserInfo) {
    Row(
        modifier = Modifier.height(60.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(modifier = Modifier.clip(CircleShape).size(60.dp).background(Color.Gray))
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
            Text("手持巨斧的大将军")
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(2) {
                    UserTag(text = "参与10个课程")
                }
            }
        }
    }
}

@Composable
private fun UserInfoLoading() {
    Row(
        modifier = Modifier.height(60.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(modifier = Modifier.clip(CircleShape).size(60.dp).background(Color.Gray).shimmerBackground())
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
            Text("", modifier = Modifier.fillMaxWidth(.5f).shimmerBackground())
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(2){
                    UserTag(text = " ", color = Color.Unspecified,modifier = Modifier.width(40.dp).shimmerBackground())
                }
            }
        }
    }
}