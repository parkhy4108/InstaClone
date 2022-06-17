plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}
android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        applicationId = "com.devyoung.instaclone"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(project(":domain"))
    implementation(project(":base"))
    implementation(project(":feeds"))
    implementation(project(":login"))
    implementation(project(":profile"))
//    implementation (Libs.coreKTX)
//    implementation (Libs.Compose.ui)
//    implementation (Libs.Compose.material)
//    implementation (Libs.Compose.preview)
//    implementation (Libs.lifecycleRuntime)
//    implementation (Libs.activityCompose)
    //Hilt
    implementation(Libs.Hilt.hiltAndroid)
    implementation(Libs.Hilt.navigationCompiler)
    kapt(Libs.Hilt.hiltCompiler)
    //Room
    implementation(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    kapt(Libs.Room.compiler)

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
