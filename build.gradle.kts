buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath ("org.jlleitschuh.gradle:ktlint-gradle:10.3.0")
        classpath ("com.google.gms:google-services:4.3.10")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "7.2.0" apply false
    id ("com.android.library") version "7.2.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.6.21" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.21" apply false
    id ("org.jlleitschuh.gradle.ktlint") version "10.3.0" apply false

}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}

