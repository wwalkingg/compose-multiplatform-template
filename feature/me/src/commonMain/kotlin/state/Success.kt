package state

import LocalSnackBarHostState
import UserInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import baseUrl
import httpClient
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import myImage.MyImage
import rememberImagePickerState
import utils.error
import utils.success
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Success(
    modifier: Modifier = Modifier,
    userInfo: UserInfo,
    onUserInfoChange: (avatar: InputStream?, name: String) -> Unit
) {
    val snackbarHostState = LocalSnackBarHostState.current
    val scope = rememberCoroutineScope()
    val imagePickerState = rememberImagePickerState()
    var modifiedUserInfo by remember(userInfo) { mutableStateOf(userInfo) }
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(imagePickerState.inputStream?.readBytes()?.size.toString())
        Box(
            modifier = Modifier.requiredSize(200.dp).clip(CircleShape)
        ) {
            MyImage(
                modifier = Modifier.fillMaxSize().clip(CircleShape).alpha(.8f),
                model = baseUrl + userInfo.avatar,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            if (imagePickerState.painter != null) {
                Image(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer)
                        .clip(CircleShape).alpha(.8f).zIndex(10f).offset(x = 5.dp),
                    painter = imagePickerState.painter!!,
                    contentDescription = null
                )
            }
            TextButton(onClick = { imagePickerState.pick() }, modifier = Modifier.align(Alignment.Center).zIndex(11f)) {
                Text("点击上传头像", color = MaterialTheme.colorScheme.onError)
            }
        }
        Spacer(Modifier.height(20.dp))
        Spacer(Modifier.height(10.dp))
        TextField(
            value = modifiedUserInfo.name,
            onValueChange = { modifiedUserInfo = modifiedUserInfo.copy(name = it) },
            label = { Text("输入你的新名字") },
            placeholder = { Text(userInfo.name) })
        Spacer(Modifier.height(10.dp))
        TextField(
            value = modifiedUserInfo.age.toString(),
            onValueChange = { modifiedUserInfo = modifiedUserInfo.copy(age = it.toIntOrNull() ?: 0) },
            label = { Text("年龄") },
            placeholder = { Text(userInfo.age.toString()) })
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("可以被搜索到")
            Switch(
                checked = modifiedUserInfo.isPartner,
                onCheckedChange = { modifiedUserInfo = modifiedUserInfo.copy(isPartner = it) }
            )
        }
        Spacer(Modifier.height(10.dp))
        TextButton(onClick = {
            if (imagePickerState.inputStream == null && modifiedUserInfo == userInfo) {
                scope.launch {
                    snackbarHostState.showSnackbar("还没进行信息更改")
                }
            } else {
                if (imagePickerState.inputStream != null) {
                    scope.launch {
                        httpClient.post("/filter/uploadAvatar") {
                            println(imagePickerState.inputStream!!.readBytes())
                            setBody(MultiPartFormDataContent(
                                formData { append("file", imagePickerState.inputStream!!.readBytes()) }
                            ))
                        }.success {
                            snackbarHostState.showSnackbar("修改头像成功")
                        }.error {
                            snackbarHostState.showSnackbar("修改头像失败")
                        }
                    }
                }
//                scope.launch {
//                    httpClient.post("/filter/updateUserInfo") {
//                        contentType(ContentType.Application.Json)
//                        setBody(modifiedUserInfo)
//                    }.success {
//                        snackbarHostState.showSnackbar("修改成功")
//                    }
//                }
            }

        }) {
            Text("确认修改")
        }
    }
}