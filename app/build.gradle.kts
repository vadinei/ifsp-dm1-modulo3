import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.vadinei.dm1.modulo3"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.vadinei.dm1.modulo3"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val propertie = Properties()
        propertie.load(project.rootProject.file( "local.properties" ).inputStream())
        buildConfigField( "String" , "INVERTEXTO_TOKEN" , propertie.getProperty( "INVERTEXTO_TOKEN" ))
    }

    buildFeatures {
        buildConfig = true
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Bibliotecas
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}