plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.abrebo.nbadatabase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.abrebo.nbadatabase"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
        viewBinding=true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.gridlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.activity.ktx)
    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    //others
    implementation ("com.airbnb.android:lottie:3.4.0")
    implementation("com.google.android.gms:play-services-ads:22.2.0")
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
}