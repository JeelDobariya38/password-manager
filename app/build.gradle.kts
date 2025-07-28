import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.GradleException

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    // If you use Kotlin Parcelize, add id("kotlin-parcelize")
}

android {
    compileSdk = 33
    namespace = "com.jeeldobariya.passcodes"

    defaultConfig {
        applicationId = "com.jeeldobariya.passcodes"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "0.1.0-Alpha"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    signingConfigs {
        create("release") {
            val keystorePropertiesFile = rootProject.file("keystore.properties")
            if (keystorePropertiesFile.exists()) {
                val keystoreProperties = Properties()
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))

                keyAlias = keystoreProperties["keyAlias"].toString()
                keyPassword = keystoreProperties["keyPassword"].toString()
                storeFile = file(keystoreProperties["storeFile"].toString())
                storePassword = keystoreProperties["storePassword"].toString()
            } else {
                logger.warn("WARNING: keystore.properties not found for release signing config.")
                // throw GradleException("keystore.properties not found!")
            }
        }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            isUniversalApk = true
        }
    }

    lint {
        // baseline = rootProject.file("lint-baseline.xml") // If you use a baseline
        lintConfig = rootProject.file("lint.xml")
    }

    buildTypes {
        release {
            if (rootProject.file("keystore.properties").exists()) {
                signingConfig = signingConfigs.getByName("release")
            } else {
                logger.warn("WARNING: Release build will not be signed as keystore.properties is missing.")
            }

            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
            manifestPlaceholders["appLabel"] = "@string/app_name"
        }

        debug {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-Dev"
            isMinifyEnabled = false

            manifestPlaceholders["appIcon"] = "@mipmap/dev_ic_launcher"
            manifestPlaceholders["appLabel"] = "Passcodes Dev"
        }

        create("staging") {
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-Staging"

            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            manifestPlaceholders["appIcon"] = "@mipmap/dev_ic_launcher"
            manifestPlaceholders["appLabel"] = "Passcodes Staging"
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
        viewBinding = true
    }
}

dependencies {
    val kotlinVersion = "1.9.0"
    val materialVersion = "1.12.0"
    val appCompatVersion = "1.7.0"
    val roomVersion = "2.7.2"
    val jsonVersion = "20250517"
    val junitVersion = "4.13.2"
    val truthVersion = "1.4.4"
    val androidxTestExtJunitVersion = "1.2.1"
    val espressoCoreVersion = "3.6.1"

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    // viewbinding is often not explicitly needed here if buildFeatures.viewBinding = true
    // implementation("androidx.databinding:viewbinding:7.4.1")

    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    implementation("org.json:json:$jsonVersion")

    testImplementation("junit:junit:$junitVersion")
    testImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidxTestExtJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCoreVersion")
}
