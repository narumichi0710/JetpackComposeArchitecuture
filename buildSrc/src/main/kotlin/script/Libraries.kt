package script

/**
 * ライブラリのバージョンを管理するオブジェクト
 */

object Libraries {

    enum class Libs(val lib: String, val optional: String = "") {
        Junit("junit:junit:4.13.2"),
        AndroidxJunit("androidx.test.ext:junit:1.1.3"),
        EspressoCore("androidx.test.espresso:espresso-core:3.4.0"),
        Timber("com.jakewharton.timber:timber:5.0.1")
    }

    enum class Props(val prop: String) {
        Api("api"),
        Impl("implementation"),
        TestImpl("testImplementation"),
        AndroidTestImpl("androidTestImplementation")
    }

    fun provideLibs(props: Props, libs: Libs) {
        if (libs.optional.isEmpty()) props.prop to (libs.lib)
        else props.prop to ("kotlin" to (listOf(libs.lib, libs.optional)))
    }
}