## ResourceLoader

### How to use

add this code in your build.gradle.kts

```kotlin
 android {
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
}
 ```

then put your resource in `src/commonMain/resources`. The resource directory must comply with the requirements of Android.