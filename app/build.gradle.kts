plugins {
    kotlin("kapt")
    id("kotlin-android")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("dev.shreyaspatil.compose-compiler-report-generator")
}

android {
    namespace = "com.yogigupta1206.invoicereceiptmaker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yogigupta1206.invoicereceiptmaker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        kapt {
            includeCompileClasspath = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                )
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/native-image/**"
        }
    }
}

dependencies {

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.navigationCompose)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.androidTestExtJunitKtx)
    testImplementation(Dependencies.androidTestRunner)
    testImplementation(Dependencies.androidTestRules)
    androidTestImplementation(Dependencies.androidTestExtJunit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)

    // Hilt Dependencies
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    // Room Dependencies
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    annotationProcessor(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)

    // Datastore Preferences
    implementation(Dependencies.datastorePreferences)

    //Gson
    implementation(Dependencies.gson)

    // iText-Core
    implementation(Dependencies.itext7Core)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}