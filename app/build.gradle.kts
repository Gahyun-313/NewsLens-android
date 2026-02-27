plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
}

android {
    namespace   = "com.newslens"
    compileSdk  = 35

    defaultConfig {
        applicationId   = "com.newslens.app"
        minSdk          = 26
        targetSdk       = 35
        versionCode     = 1
        versionName     = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val baseUrl = project.findProperty("BASE_URL") as? String
            ?: "https://api.newslens.com/"
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
    }

    buildTypes {
        debug {
            isDebuggable        = true
            applicationIdSuffix = ".debug"
            versionNameSuffix   = "-debug"
        }
        release {
            isMinifyEnabled   = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose     = true
        buildConfig = true
    }

    testOptions {
        unitTests.isReturnDefaultValues    = true
        unitTests.isIncludeAndroidResources = true
    }
}

ktlint {
    version.set("1.3.1")
    android.set(true)
    outputColorName.set("RED")
}

dependencies {
    // ─── AndroidX ─────────────────────────────────
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.bundles.lifecycle)

    // ─── Compose ──────────────────────────────────
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.core)
    implementation(libs.androidx.navigation.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // ─── DI (Hilt) ────────────────────────────────
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // ─── Network ──────────────────────────────────
    implementation(libs.bundles.network)
    implementation(platform(libs.okhttp.bom))

    // ─── Database (Room) ──────────────────────────
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // ─── DataStore ────────────────────────────────
    implementation(libs.datastore.preferences)

    // ─── Image ────────────────────────────────────
    implementation(libs.coil.compose)

    // ─── Coroutines ───────────────────────────────
    implementation(libs.kotlinx.coroutines.android)

    // ─── Unit Test ────────────────────────────────
    testImplementation(libs.bundles.test.unit)

    // ─── Android Test ─────────────────────────────
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.test.android)
}
