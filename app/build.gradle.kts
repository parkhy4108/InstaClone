plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
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
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    hilt {
        enableAggregatingTask =true
    }

}
dependencies {
    implementation(project(":base"))
    implementation(project(":feeds"))
    implementation(project(":login"))
    implementation(project(":profile"))
    implementation(project(":search"))
    implementation(Libs.Hilt.hiltAndroid)
    implementation(Libs.Hilt.navigationCompiler)
    kapt(Libs.Hilt.hiltCompiler)
    implementation(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    kapt(Libs.Room.compiler)
    kapt(Libs.lifecycleCompiler)
    kapt(Libs.Glide.compiler)
    testImplementation(Libs.Test.Junit)
    androidTestImplementation(Libs.Test.extJunit)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(Libs.Compose.testJunit)
    debugImplementation(Libs.Compose.uiTooling)
    debugImplementation(Libs.Compose.testManifest)
}
kapt {
    correctErrorTypes = true
}
