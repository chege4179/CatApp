/*
 * Copyright 2023 Pussy Cat App By Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.peterchege.pussycatapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.peterchege.pussycatapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val compose_version = "1.4.2"
    implementation ("androidx.core:core-ktx:1.10.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.0")
    implementation ("androidx.compose.ui:ui:1.5.0-alpha02")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0-alpha02")
    implementation ("androidx.compose.material3:material3:1.1.0-beta02")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.5.0-alpha02")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.0-alpha02")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.0-alpha02")


    val  ktor_version = "2.2.4"
    implementation ("io.ktor:ktor-client-core:$ktor_version")
    implementation ("io.ktor:ktor-client-android:$ktor_version")
    implementation ("io.ktor:ktor-client-serialization:$ktor_version")
    implementation ("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation ("ch.qos.logback:logback-classic:1.2.3")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("io.coil-kt:coil-compose:2.2.2")

    //paging 3
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha18")

    //koin
    implementation ("io.insert-koin:koin-android:3.3.3")
    implementation ("io.insert-koin:koin-core:3.3.3")
    implementation ("io.insert-koin:koin-androidx-compose:3.4.2")


    debugImplementation ("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:3.5.2")
}