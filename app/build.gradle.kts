plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.collapsingtoolbar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iris.collapsingtoolbar"
        minSdk = 28
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
        viewBinding.enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("se.emilsjolander:StickyScrollViewItems:1.1.0")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    val coilVer = "2.6.0"
    implementation("io.coil-kt:coil:$coilVer")

    val viewPager2Ver = "1.1.0"
    implementation("androidx.viewpager2:viewpager2:$viewPager2Ver")

    val dotsIndicatorVer = "5.0"
    implementation("com.tbuonomo:dotsindicator:$dotsIndicatorVer")
}