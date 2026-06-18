pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.google.android.gms.oss-licenses-plugin") {
                useModule("com.google.android.gms:oss-licenses-plugin:0.12.0")
            }
        }
    }

}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "WearFiles"
include(":app")
include(":core:design")
include(":feature:onboarding")
include(":core:data")
include(":feature:file_list")
include(":core:navigation")
include(":feature:text_viewer")
include(":feature:menu")
include(":feature:rename")
include(":feature:new_directory")
include(":feature:delete")
include(":feature:home")
include(":feature:images")
include(":feature:music")
include(":feature:video")
include(":feature:image_viewer")
include(":feature:theming")
include(":feature:settings")
include(":feature:pdf_viewer")
include(":app_mobile")
