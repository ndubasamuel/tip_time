import org.jetbrains.kotlin.builtins.StandardNames.FqNames.set
import org.jetbrains.kotlin.cli.jvm.main

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs")
    id ("kotlin-kapt")
}

android {

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("src/main/jniLibs")
        }
    }
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")

//            version = "3.22.1"
        }
    }

    namespace = "com.example.tiptime"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.tiptime.onboarding"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        val base64EncodedApiKey: Any = project.findProperty("BASE64_ENCODED_API_KEY") ?: ""
//        buildConfigField("String", "BASE64_ENCODED_API_KEY", "\"${base64EncodedApiKey}\"")
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
        
        


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            create("customDebugType") {
                isDebuggable = true
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    var ndk = "26.2.11394342"
    ndkVersion = ndk


}





dependencies {

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("androidx.room:room-common:2.4.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    Shared preferences
    implementation ("androidx.security:security-crypto:1.0.0-alpha02")

//    jni
//    implementation ("com.android.support:appcompat-v7:10.0.0")


    //navigation dependencies
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    //picasso
    implementation("com.squareup.picasso:picasso:2.8")

    //gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //okHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")


    //appstore updates
    implementation("com.google.android.play:app-update-ktx:2.1.0")


//    Network
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit-converters:2.6.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.3.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

//    Image cache
    implementation ("com.github.bumptech.glide:glide:4.8.0")
    kapt ("androidx.annotation:annotation:1.7.1")
    kapt ("com.github.bumptech.glide:compiler:4.8.0")

//    Dagger
    kapt ("com.google.dagger:dagger-compiler:2.48.1")
    implementation ("com.google.dagger:dagger:2.48.1")
    implementation ("com.google.dagger:dagger-android:2.48.1")
    implementation ("com.google.dagger:dagger-android-support:2.48.1")
    kapt ("com.google.dagger:dagger-android-processor:2.48.1")
    kapt("com.google.dagger:dagger-compiler:2.48.1")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")

//    Data Persistence
//    implementation ("android.arch.persistence.room:runtime:1.1.0")
//    kapt ("android.arch.persistence.room:compiler:1.0.0")
    kapt ("android.arch.persistence.room:compiler:$rootProject.roomVersion")
    implementation ("androidx.room:room-runtime:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")

//    Anko injection
//    implementation ("org.jetbrains.anko:anko-commons:0.10.8")
//    implementation ("org.jetbrains.anko:anko-common:0.9")
//    RXJava
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation ("android.arch.persistence.room:runtime:1.1.1")
    implementation ("android.arch.persistence.room:rxjava2:1.1.1")
    kapt ("android.arch.persistence.room:compiler:1.1.1")
    implementation("androidx.room:room-ktx:2.5.0")
    implementation ("androidx.room:room-rxjava3:2.3.0")


//    glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")

//    RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")



}


