package script

/**
 * ライブラリのバージョンを管理するオブジェクト
 */

object Libraries {

    const val impl = "implementation"
    const val testImpl = "testImplementation"
    const val androidTestImpl = "androidTestImplementation"
    const val junit = "junit:junit:4.13.2"
    const val androidxJunit = "androidx.test.ext:junit:1.1.3"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"


    fun provideImpl(library: String, impl: String) = impl.plus((library))
}