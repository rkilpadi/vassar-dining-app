plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "vassar.cmpu203.vassardiningapp"
    compileSdk = 33
    defaultConfig {
        applicationId = "vassar.cmpu203.vassardiningapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    implementation(project(":shared"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity-compose:1.7.1")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.fragment:fragment-ktx:1.5.7")
    implementation("androidx.concurrent:concurrent-futures:1.1.0")
    implementation("com.google.guava:guava:30.1-android")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.20")

    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.compose.compiler:compiler:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.navigation:navigation-compose:2.6.0-rc01")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
}