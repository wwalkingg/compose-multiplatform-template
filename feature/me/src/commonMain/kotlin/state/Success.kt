package state

import UserInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import baseUrl
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import rememberImagePickerState
import shimmerBackground

@Composable
//internal fun Success(modifier: Modifier = Modifier, userInfo: UserInfo) {
internal fun Success(modifier: Modifier = Modifier) {
    val imagePickerState = rememberImagePickerState()
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.requiredSize(200.dp).clip(CircleShape)
        ) {
            KamelImage(
                modifier = Modifier.fillMaxSize().clip(CircleShape).alpha(.8f),
                resource = lazyPainterResource(baseUrl + ""),
                contentDescription = null,
                onFailure = {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.error) {
                        Text("userInfo.name".first().toString())
                    }
                },
                onLoading = {
                    Box(modifier = Modifier.fillMaxSize().shimmerBackground())
                }
            )
            TextButton(onClick = { imagePickerState.pick() }, modifier = Modifier.align(Alignment.Center)) {
                Text("点击上传头像", color = MaterialTheme.colorScheme.onError)
            }
        }

    }
}