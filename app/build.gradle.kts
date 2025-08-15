import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.GradleException
import com.android.build.api.dsl.ApplicationExtension

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    // If you use Kotlin Parcelize, uncomment the next line:
    // id("kotlin-parcelize")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    (this as ApplicationExtension).apply {
        compileSdk = 36
        namespace = "com.jeeldobariya.passcodes"

        defaultConfig {
            applicationId = "com.jeeldobariya.passcodes"
            minSdk = 26
            targetSdk = 34
            versionCode = 1
            versionName = "v1.0.0-Stable"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        signingConfigs {
            create("release") {
                val keystorePropertiesFile = rootProject.file("keystore.properties")
                if (keystorePropertiesFile.exists()) {
                    val keystoreProperties = Properties()
                    keystoreProperties.load(FileInputStream(keystorePropertiesFile))

                    keyAlias = keystoreProperties.getProperty("keyAlias")
                    keyPassword = keystoreProperties.getProperty("keyPassword")
                    storeFile = file(keystoreProperties.getProperty("storeFile"))
                    storePassword = keystoreProperties.getProperty("storePassword")
                } else {
                    logger.warn("WARNING: keystore.properties not found for release signing config.")
                    // throw GradleException("keystore.properties not found!")
                }
            }

            create("staging") {
                val keystorePropertiesFile = rootProject.file("keystore.properties")
                if (keystorePropertiesFile.exists()) {
                    val keystoreProperties = Properties()
                    keystoreProperties.load(FileInputStream(keystorePropertiesFile))

                    keyAlias = keystoreProperties.getProperty("stagingKeyAlias")
                    keyPassword = keystoreProperties.getProperty("stagingKeyPassword")
                    storeFile = file(keystoreProperties.getProperty("stagingStoreFile"))
                    storePassword = keystoreProperties.getProperty("stagingStorePassword")
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
            getByName("release") {
                if (rootProject.file("keystore.properties").exists()) {
                    signingConfig = signingConfigs.getByName("release")
                } else {
                    logger.warn("WARNING: Release build will not be signed as keystore.properties is missing.")
                    // throw GradleException("Can't Sign Release Build")
                }

                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

                // Use manifestPlaceholders.put() for key-value pairs
                manifestPlaceholders.put("appIcon", "@mipmap/ic_launcher")
                manifestPlaceholders.put("appLabel", "@string/app_name")
            }

            getByName("debug") {
                applicationIdSuffix = ".dev"
                versionNameSuffix = "-Dev"
                isMinifyEnabled = false

                manifestPlaceholders.put("appIcon", "@mipmap/dev_ic_launcher")
                manifestPlaceholders.put("appLabel", "Passcodes Dev")
            }

            create("staging") {
                if (rootProject.file("keystore.properties").exists()) {
                    signingConfig = signingConfigs.getByName("staging")
                } else {
                    logger.warn("WARNING: Staging build will not be signed as keystore.properties is missing.")
                    // throw GradleException("Can't Sign Staging Build")
                }

                applicationIdSuffix = ".staging"
                versionNameSuffix = "-Staging"

                isMinifyEnabled = true
                isShrinkResources = true
                isDebuggable = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

                manifestPlaceholders.put("appIcon", "@mipmap/dev_ic_launcher")
                manifestPlaceholders.put("appLabel", "Passcodes Staging")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        buildFeatures {
            viewBinding = true
        }
    }

    ksp {
        val location = "$projectDir/schemas"
        arg("room.schemaLocation", location)
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    val kotlinVersion = "1.9.0"
    val materialVersion = "1.12.0"
    val ossLicenseVersion = "17.2.1"
    val appCompatVersion = "1.7.0"
    val roomVersion = "2.7.2"
    // val jsonVersion = "20250517"
    val junitVersion = "4.13.2"
    val truthVersion = "1.4.4"
    val androidxTestExtJunitVersion = "1.2.1"
    val espressoCoreVersion = "3.6.1"

    val coroutinesVersion = "1.10.2"
    val lifecycleVersion = "2.9.2"

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    implementation("com.google.android.material:material:$materialVersion")
    implementation("com.google.android.gms:play-services-oss-licenses:$ossLicenseVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    // viewbinding is often not explicitly needed here if buildFeatures.viewBinding = true
    // implementation("androidx.databinding:viewbinding:7.4.1")

    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Lifecycle components for lifecycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    // implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion") // Good for future ViewModels

    // // External JSON library
    // implementation("org.json:json:$jsonVersion") // Standard org.json library

    // Testing dependencies
    testImplementation("junit:junit:$junitVersion")
    testImplementation("com.google.truth:truth:$truthVersion")
    
    // Room testing
    androidTestImplementation("androidx.room:room-testing:$roomVersion") // Essential for Room testing
    androidTestImplementation("androidx.test.ext:junit:$androidxTestExtJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCoreVersion")

    // Coroutine test utilities (for runTest)
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    // Assertion library (Google Truth)
    androidTestImplementation("com.google.truth:truth:$truthVersion")
}
