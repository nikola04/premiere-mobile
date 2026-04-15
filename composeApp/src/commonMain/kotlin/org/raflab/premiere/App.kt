package org.raflab.premiere

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.raflab.premiere.di.appModule
import org.raflab.premiere.navigation.AppNavigation

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme(colorScheme = darkColorScheme()) {
            AppNavigation()
        }
    }
}