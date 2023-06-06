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
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.plugin.serialization")
}
val key: String = gradleLocalProperties(rootDir).getProperty("API_KEY")
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
        getByName("debug"){
            buildConfigField("String", "API_KEY", key)
        }
        getByName("release") {
            buildConfigField("String", "API_KEY", key)
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
            excludes.add("/META-INF/**")
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.compose.ui:ui:1.5.0-beta01")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0-beta01")
    implementation ("androidx.compose.material3:material3:1.2.0-alpha02")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.5.0-beta01")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.0-beta01")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.0-beta01")


    val  ktor_version = "2.2.4"
    implementation ("io.ktor:ktor-client-core:$ktor_version")
    implementation ("io.ktor:ktor-client-android:$ktor_version")
    implementation ("io.ktor:ktor-client-serialization:$ktor_version")
    implementation ("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation ("ch.qos.logback:logback-classic:1.3.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("io.coil-kt:coil-compose:2.3.0")

    //paging 3
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha20")

    //koin
    implementation ("io.insert-koin:koin-android:3.4.0")
    implementation ("io.insert-koin:koin-core:3.4.0")
    implementation ("io.insert-koin:koin-androidx-compose:3.4.3")


    debugImplementation ("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:3.5.2")
}