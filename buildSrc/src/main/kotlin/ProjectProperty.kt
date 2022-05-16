import com.android.build.gradle.internal.dsl.BuildType

object ProjectProperty {

    /**
     * アプリのリリースなどで使うID
     */
    const val applicationId = "com.narumichi.jetpackcomposearchitecuture"

    /**
     * バージョンコード
     */
    const val versionCode = 1

    /**
     * リリースバージョン
     */
    const val versionName = "1.0"

    /**
     * ターゲットバージョン
     */
    const val targetSdkVersion = 30

    /**
     * コンパイルバージョン
     */
    const val compileSdkVersion = 31

    /**
     * 最低SDKバージョン
     */
    const val minSdkVersion = 26

    /**
     * ハードコードをなるべく行わないようプロパティに定義したもの
     */
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"
    const val proguardAndroid = "proguard-android.txt"
    const val proguardRules = "proguard-rules.pro"
    const val android = "android"
    const val environment = "environment"

    /**
     * FlavorType一覧
     */
    internal enum class FlavorType {
        prod, stg, dev
    }

    /**
     * プロジェクトで利用するBuildType一覧
     */
    internal enum class ProjectBuildType(val action: (BuildType, FlavorType) -> Unit) {
        mock({ _, _ -> }),
        debug({ _, _ -> }),
        release({ _, _ -> })
    }

}