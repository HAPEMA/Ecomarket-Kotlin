plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.ecomarket"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.ecomarket"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(libs.androidx.navigation.compose)
    debugImplementation("androidx.compose.ui:ui-tooling")
    // Material 3
    implementation("androidx.compose.material3:material3")

    // 🧩 Extensión para íconos como CameraAlt, Image, etc.
    implementation("androidx.compose.material:material-icons-extended")

    // Para los campos de texto con teclado (KeyboardOptions)
    implementation("androidx.compose.ui:ui-text")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // ViewModel + runtime Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")

    // Coil para imágenes
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Lazy grids
    implementation("androidx.compose.foundation:foundation")
}
