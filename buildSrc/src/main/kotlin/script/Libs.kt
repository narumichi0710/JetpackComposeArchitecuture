package script

/**
 * ライブラリのバージョンを管理するオブジェクト
 */

object Libs {

    object Versions {
        const val kotlin = "1.6.20"
        const val junit = "4.13.2"
        const val espresso = "3.4.0"
        const val lifeCycle = "2.4.0"
        const val compose = "1.1.0-rc01"
        const val hilt = "2.40.5"
        const val hiltLifecycle = "1.0.0-alpha03"
        const val hiltCompose = "1.2.0"
        const val timber = "5.0.1"
        const val material = "1.4.0"
        const val androidXCore = "1.7.0"
        const val androidXAppCompat = "1.4.0"
        const val gradle = "7.0.4"
        const val coroutine = "1.6.0"
        const val okhttp = "4.9.2"
        const val retrofit = "2.9.0"
        const val moshi = "1.13.0"
        const val robolectric = "1.0.0"
        const val mockito = "1.10.19"
    }

    object Plugin {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"

        object Hilt {
            const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
            const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        }
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidXCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidXAppCompat}"
        const val navigationFragment = "androidx.navigation:navigation-fragment:2.4.1"

        object Hilt {
            const val lifecycleViewModel =
                "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycle}"
            const val navigationCompose =
                "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"
        }

        object LifeCycle {
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
            const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifeCycle}"
        }

        object Compose {
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
            const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
            const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val test = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        }
    }

    object Coroutine {
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val robolectric = "androidx.test:core:${Versions.robolectric}"
        const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Other {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    object Square {

        object OkHttp {
            const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
            const val loggingInterceptor =
                "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        }

        object Retrofit {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        }

        object Moshi {
            const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        }
    }

    enum class Props(val prop: String) {
        Api("api"),
        Impl("implementation"),
        Kapt("kapt"),
        TestImpl("testImplementation"),
        AndroidTestImpl("androidTestImplementation"),
        AndroidTestApi("androidTestApi")
    }
}