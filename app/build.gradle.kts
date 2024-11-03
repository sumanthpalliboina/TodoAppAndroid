import com.android.build.api.dsl.ViewBinding

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sumanthacademy.myapplication"
    compileSdk = 34

    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.sumanthacademy.myapplication"
        minSdk = 24
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
}

dependencies {

    val lottieVersion = "6.5.2"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.28")
    //implementation("com.romandanylyk:pageindicatorview:0.0.7")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    implementation("com.airbnb.android:lottie:$lottieVersion")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // https://mvnrepository.com/artifact/org.greenrobot/eventbus
    implementation("org.greenrobot:eventbus:3.2.0")

    implementation("com.github.romandanylyk:PageIndicatorView:v1.0.1")

    /*implementation("com.android.support:appcompat-v7:27.1.1")
    implementation("com.android.support:recyclerview-v7:27.1.1")
    implementation("com.android.support:support-core-ui:27.1.1")*/
}