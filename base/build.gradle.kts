plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
    }
}
dependencies {
    api(Libs.coreKTX)
    api(Libs.Compose.ui)
    api(Libs.Compose.material)
    api(Libs.lifecycleRuntime)
    api(Libs.lifecycleState)
    api(Libs.activityCompose)
    api(Libs.Compose.navigation)
    api(Libs.Compose.livedata)
    api(platform(Libs.FireBase.bom))
    api(Libs.FireBase.analyticsKtx)
    api(Libs.FireBase.auth)
    api(Libs.FireBase.authKTX)
    api(Libs.FireBase.firestoreKTX)
    api(Libs.FireBase.storageKTX)
    api(Libs.skydovesGlide)
    api(Libs.Glide.glide)

    testImplementation(Libs.Test.Junit)
    androidTestImplementation(Libs.Test.extJunit)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(Libs.Compose.testJunit)
    debugImplementation(Libs.Compose.uiTooling)
    debugImplementation(Libs.Compose.testManifest)
}
