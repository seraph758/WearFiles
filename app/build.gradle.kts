import org.jetbrains.kotlin.gradle.dsl.JvmTarget

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
}

android {
    namespace = "com.dertefter.wearfiles"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.dertefter.wearfiles"
        minSdk = 28
        targetSdk = 36
        versionCode = 26
        versionName = "2.0.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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

    implementation(project(":feature:onboarding"))
    implementation(project(":feature:file_list"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.horologist.compose.layout)
    
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.wear.remote.interactions)


    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
