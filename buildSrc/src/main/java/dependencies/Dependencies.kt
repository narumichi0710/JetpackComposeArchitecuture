package dependencies

/**
 * NOTE:
 * 全モジュールで利用するライブラリを定義するファイル
 * 現状はプロジェクト直下のktsで定義しているが、今後使うかもしれないのでコメントアウトしておく
 */

//fun Project.baseDependencies(additionalConfiguration: DependencyHandlerScope.() -> Unit) {
//    dependencies {
//        implementation(Libs.Kotlin.stdlib)
//        implementation(Libs.AndroidX.appCompat)
//        implementation(Libs.AndroidX.coreKtx)
//        implementation(Libs.AndroidX.constraint)
//    }
//    dependencies(additionalConfiguration)
//}
//
//private fun DependencyHandler.implementation(depName: Any) {
//    add("implementation", depName)
//}