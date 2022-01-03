package script

/**
 * ライブラリのバージョンを管理するオブジェクト
 */

object Libs {

    object Versions {
        const val kapt = "1.6.10"
        const val junit = "4.13.2"
        const val espresso = "3.4.0"
        const val hilt = "2.40.5"
        const val hiltLifecycle = "1.0.0-alpha03"
        const val hiltCompose = "1.0.0-rc01"
        const val timber = "5.0.1"
        const val material = "1.4.0"
        const val androidXCore = "1.7.0"
        const val androidXAppCompat = "1.4.0"
        const val gradle = "7.0.4"
        const val gradlePlugin = "1.6.0"
    }

    object Plugin {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.gradlePlugin}"
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

        object Hilt {
            const val lifecycleViewModel =
                "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycle}"
            const val navigationCompose =
                "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"
        }
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Other {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    enum class Props(val prop: String) {
        Api("api"),
        Impl("implementation"),
        Kapt("kapt"),
        TestImpl("testImplementation"),
        AndroidTestImpl("androidTestImplementation")
    }

}