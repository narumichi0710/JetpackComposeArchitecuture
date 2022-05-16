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

    /**
     * プリミティブ型の一覧
     */
    internal enum class BuildConfigType : IBuildConfigType {
        Boolean, String
    }

    /**
     * 型定義のクラスをマーキングするためのインターフェース
     */
    interface IBuildConfigType

    /**
     * URLのパスの向き先を定義する
     * WebViewやCDNやSaaS用の種類が追加される想定
     */
    private enum class UrlType {
        API
    }

    /**
     * 環境毎に様々な型の値を出せるが、複雑なものは環境毎のパッケージを作ってKotlinで書いた方が保守性が高い
     */
    internal enum class CustomBuildConfigType(val fileFullPath: String) : IBuildConfigType {
        StringArray("java.util.ArrayList<String>")
    }

    /**
     * 環境変数一覧
     * プロパティの型と、環境別で値の出し分けロジックを書く
     * ラムダの中の返り値のStringをパースしたものをそのままプロダクト実装コードで使える
     */
    internal enum class BuildConfig(
        val type: IBuildConfigType,
        val value: (FlavorType, ProjectBuildType) -> String
    ) {
        IS_DEBUG_LOGGING(BuildConfigType.Boolean, { flavorType, _ ->
            (flavorType != FlavorType.prod).toString()
        }),
        BASE_URL(BuildConfigType.String, { flavorType, _ ->
            if (flavorType == FlavorType.prod) baseUrl(UrlType.API, null)
            else baseUrl(UrlType.API, flavorType.name)
        }),
        ArrayMock(CustomBuildConfigType.StringArray, {_, _ -> "new java.util.ArrayList<>()"})
    }

    /**
     * APIのパスを生成する関数。
     * 第一引数で用途毎の出し分け、
     * 第二引数で「dev」や「stg」を埋め込む想定。
     */
    private fun baseUrl(urlType: UrlType, prefix: String?): String = when (urlType) {
        UrlType.API -> "https://${prefix ?: ""}.arsaga.jp/v1/api/"
    }.let { "\"$it\"" }

    /**
     * ManifestPlaceHolderに置く値の一覧
     */
    internal enum class ManifestPlaceHolderType(val value: (FlavorType, ProjectBuildType) -> String) {
        appName({ flavorType, _ ->
            if (flavorType == FlavorType.prod) {
                ""
            } else {
                flavorType.name
            }.plus("テンプレート")
        }),
        deepLinkHost({ _, _ ->
            ""
        }),
    }

}