plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
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
    implementation(project(":base"))
    implementation(Libs.Hilt.hiltAndroid)
    implementation(Libs.Hilt.navigationCompiler)
    kapt(Libs.Hilt.hiltCompiler)
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
