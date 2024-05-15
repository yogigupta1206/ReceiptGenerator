object Dependencies {

    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.composeBom}" }
    val composeUi by lazy { "androidx.compose.ui:ui" }
    val composeUiGraphics by lazy { "androidx.compose.ui:ui-graphics" }
    val composeUiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
    val composeMaterial3 by lazy { "androidx.compose.material3:material3" }
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val androidTestExtJunitKtx by lazy { "androidx.test.ext:junit-ktx:${Versions.androidTestExtJunit}" }

    //    testImplementation 'androidx.test:runner:1.4.0'
    val androidTestRunner by lazy { "androidx.test:runner:${Versions.androidTestRunner}" }
    val androidTestRules by lazy { "androidx.test:rules:${Versions.androidTestRunner}" }
    val androidTestExtJunit by lazy { "androidx.test.ext:junit:${Versions.androidTestExtJunit}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }
    val composeUiTestJunit4 by lazy { "androidx.compose.ui:ui-test-junit4" }
    val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling" }
    val composeUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest" }

    val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltAndroid}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}" }

    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }

    val datastorePreferences by lazy { "androidx.datastore:datastore-preferences:${Versions.datastorePreferences}" }

    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }

    val itext7Core by lazy { "com.itextpdf:itext-core:${Versions.itext7Core}" }

}