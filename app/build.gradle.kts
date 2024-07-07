import com.android.build.api.dsl.ApplicationBuildType
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val localPropertiesFile = rootProject.file("local.properties")

android {
    namespace = "com.example.stonksapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stonksapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            buildConfigField("String", "API_KEY", getProperty("API_KEY"))
        }
        release {
            // Hardcoded API key for demonstration purposes
            buildConfigField("String", "API_KEY", getProperty("API_KEY"))
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Hilt
    implementation(libs.hilt.android)
    //implementation(libs.androidx.hilt.lifecycle.viewmodel)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)

    implementation(libs.material)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Datastore setup
    implementation(libs.jasjeet.datastore.gson)
    implementation(libs.jasjeet.datastore)
    testImplementation(libs.jasjeet.datastore.test)
    implementation(libs.androidx.datastore.preferences)

    // Web Service Setup
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.gson)
}

fun ApplicationBuildType.getProperty(name: String): String {
    val file = if (isDebuggable && localPropertiesFile.exists()) {
        localPropertiesFile
    } else if (!isDebuggable && keystorePropertiesFile.exists()) {
        keystorePropertiesFile
    } else {
        throw GradleException("Missing keystore or local.properties file")
    }

    val properties = Properties()
    properties.load(FileInputStream(file))
    return "\"${properties[name].toString()}\""
}