plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.tisto.helpers"
    compileSdk = 36

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //swiperefreshlayout
    implementation(libs.androidx.swiperefreshlayout)

    // printer bluetooth thermal
    implementation(libs.escpos.thermalprinter.android)

    // Image picker
    implementation(libs.imagepicker)

    //Sweet Alert
    implementation(libs.library)
    implementation(libs.toasty)
//    implementation(libs.motiontoast) {
//        exclude("org.jetbrains.kotlin", "kotlin-android-extensions-runtime")
//    }

    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)

    // glide
    implementation(libs.glide)

    // PermissionsManager
    implementation(libs.dexter)

    //Pixele
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
}