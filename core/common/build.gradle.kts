@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt.android.plugin)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.learn.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String","BASE_URL","\"https://api.themoviedb.org\"")
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.nework.logging)


    //retrofit
    implementation(libs.retrofit.client)
    implementation(libs.retrofit.moshi)
    implementation(libs.moshi)
    implementation(libs.coroutie)
    api(libs.viewmodel.lifecycle)
    api(libs.lifecycle.runtime)
    api(libs.activity.ktx)
    api(libs.fragment.ktx)
    api(libs.timber)




    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}