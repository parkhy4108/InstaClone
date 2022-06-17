plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")

}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":base"))

//    implementation (Libs.coreKTX)
//
//    implementation (Libs.Compose.ui)
//    implementation (Libs.Compose.material)
//    implementation (Libs.Compose.preview)
//    implementation (Libs.lifecycleRuntime)
//    implementation (Libs.activityCompose)


    //Hilt
    implementation(Libs.Hilt.hiltAndroid)
    implementation(Libs.Hilt.navigationCompiler)
    kapt(Libs.Hilt.hiltCompiler)

    testImplementation (Libs.Test.Junit)
    androidTestImplementation (Libs.Test.extJunit)
    androidTestImplementation (Libs.Test.espresso)
    androidTestImplementation (Libs.Compose.testJunit)
    debugImplementation (Libs.Compose.uiTooling)
    debugImplementation (Libs.Compose.testManifest)
}
kapt {
    correctErrorTypes = true
}