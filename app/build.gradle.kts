import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    signingConfigs {
        create("release") {
            val keystoreProperties = Properties().apply{
                load(File("keystore.properties").reader())
            }
            storeFile = File(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword= keystoreProperties.getProperty("keyPassword")
        }
    }
    namespace = "ml.matheditor.twa"
    compileSdk = 34

    defaultConfig {
        applicationId = "ml.matheditor.twa"
        minSdk = 24
        targetSdk = 34
        versionCode = 8
        versionName = "0.7.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("release")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
}

dependencies {
    implementation(libs.androidbrowserhelper)
}