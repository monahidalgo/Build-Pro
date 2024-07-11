plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.constructionsite"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.constructionsite"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packagingOptions {
        exclude("**/car-ui-lib-2.6.0/res/values/values.xml")
        exclude("**/fragment-1.8.1/res/values/values.xml")
        exclude("**/core-1.13.1/res/values/values.xml")
        exclude("**/material-1.5.0/res/values/values.xml")
        exclude("**/activity-1.9.0/res/values/values.xml")
        // ... add other exclusions if needed
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("customDebugType") {
            isDebuggable = true
            // Add other custom debug configurations here
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.core.ktx.v170)
    implementation(libs.androidx.appcompat.v141)
    implementation(libs.material.v150)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout.v213)
    implementation(libs.glide)
    implementation(libs.androidx.room.common.v242)
    implementation(libs.androidx.room.ktx.v242)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.car.ui.lib)
    implementation(libs.support.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)
    implementation(libs.material.v1xx)
    implementation(libs.androidx.fragment)
    // ... other dependencies
}
