
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
     * その他
     */
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"
    const val android = "android"

    /**
     * FlavorType一覧
     */
    internal enum class FlavorType {
        prod, stg, dev
    }
}