plugins {
    // These plugins are applied to the root project itself, often not needed for a typical Android app.
    // If you need to define shared properties or tasks for all subprojects, you can do it here.
    id("com.android.application") version "8.11.0" apply false
    // id("com.android.library") version "8.11.0" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
}

// Allprojects block is common for setting up common repositories for all subprojects.
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Task to clean the build directory, now registered using tasks.register
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

// You might put common dependency versions here using 'ext' for simpler projects,
// but Version Catalogs (libs.versions.toml) are the modern way for multi-module projects.
// For example:
// ext {
//     set("androidx_appcompat_version", "1.7.0")
//     set("material_version", "1.12.0")
//     // ... and so on
// }
