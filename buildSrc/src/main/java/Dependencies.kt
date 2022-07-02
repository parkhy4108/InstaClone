object Versions {

    const val coreKTX = "1.7.0"

    const val lifecycleRunTimeKTX = "2.3.1"

    const val activityCompose = "1.3.1"

    const val espressoCORE = "3.4.0"

    const val junit = "4.13.2"
    const val extJunit = "1.1.3"

    //Compose
    const val compose_version = "1.2.0-beta02"
    const val navigation = "2.4.2"

    //Hilt
    const val hilt = "2.42"
    const val hiltNavigationCompiler = "1.0.0"
    const val inject ="1"

    //Room
    const val room = "2.4.2"

    //Firebase
    const val firebaseBom = "30.1.0"

    //Glide
    const val skyGlide = "1.4.7"
    const val glide = "4.13.0"

}

object Libs {

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRunTimeKTX}"

    const val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"

    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose_version}"
        const val material = "androidx.compose.material:material:${Versions.compose_version}"
        const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
        const val livedata =  "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"
        const val testJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
        const val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose_version}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
    }

    object Test {
        const val Junit = "junit:junit:${Versions.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCORE}"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val navigationCompiler = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompiler}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val inject = "javax.inject:javax.inject:${Versions.inject}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object FireBase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx"
        const val auth = "com.google.firebase:firebase-auth"
        const val authKTX = "com.google.firebase:firebase-auth-ktx"
        const val firestoreKTX = "com.google.firebase:firebase-firestore-ktx"
    }

    const val facebook = "com.facebook.android:facebook-login:latest.release"
    const val skydovesGlide = "com.github.skydoves:landscapist-glide:${Versions.skyGlide}"

    object Glide{
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }


}