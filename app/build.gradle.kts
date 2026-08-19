plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.p2"
    compileSdk {
        // El compose-bom "2026.08.00" arrastra librerías (compose-ui, foundation,
        // material3-adaptive, activity, core-ktx, etc.) que exigen compilar contra
        // la API 37 o superior; con compileSdk 35 el build falla en
        // checkDebugAarMetadata. La API 37 ya está instalada en este SDK local.
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.p2"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    // Navegación adaptativa: barra inferior (móvil) / panel lateral (tablet-escritorio).
    // Versión resuelta por el compose-bom (declarada sin número de versión).
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite")
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}