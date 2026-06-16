import org.jetbrains.kotlin.gradle.dsl.JvmTarget

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.dertefter.wearfiles"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.dertefter.wearfiles"
        minSdk = 26
        targetSdk = 36
        versionCode = 45
        versionName = "3.0.4"
    }

    signingConfigs {
        create("release") {
            val envKeystoreFile = System.getenv("ANDROID_KEYSTORE_FILE")
            val envKeystorePassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
            val envKeyAlias = System.getenv("ANDROID_KEY_ALIAS")
            val envKeyPassword = System.getenv("ANDROID_KEY_PASSWORD")

            if (envKeystoreFile != null && envKeystorePassword != null && envKeyAlias != null && envKeyPassword != null) {
                storeFile = file(envKeystoreFile)
                storePassword = envKeystorePassword
                keyAlias = envKeyAlias
                keyPassword = envKeyPassword
            } else {
                val debugConfig = signingConfigs.getByName("debug")
                storeFile = debugConfig.storeFile
                storePassword = debugConfig.storePassword
                keyAlias = debugConfig.keyAlias
                keyPassword = debugConfig.keyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}



dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:design"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:home"))
    implementation(project(":feature:images"))
    implementation(project(":feature:music"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:file_list"))
    implementation(project(":feature:rename"))
    implementation(project(":feature:delete"))
    implementation(project(":feature:new_directory"))
    implementation(project(":feature:video"))
    implementation(project(":feature:theming"))
    implementation(project(":feature:settings"))

    implementation(project(":feature:text_viewer"))
    implementation(project(":feature:image_viewer"))
    implementation(project(":feature:pdf_viewer"))

    implementation(libs.material.kolor)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.wear.compose.material3)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.wear.compose.navigation)
    implementation(libs.horologist.compose.layout)
    implementation(libs.androidx.wear.remote.interactions)
    implementation(libs.play.services.wearable)
    implementation(libs.kotlinx.coroutines.play)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.gson)
    implementation(libs.androidx.core.splashscreen)
}
