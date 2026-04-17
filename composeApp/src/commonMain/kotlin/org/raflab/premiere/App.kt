package org.raflab.premiere

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.raflab.premiere.di.appModule
import org.raflab.premiere.navigation.AppNavigation

@Composable
fun App() {
    val darkTheme = isSystemInDarkTheme()

    val colorScheme = when {
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme(colorScheme) {
            AppNavigation()
        }
    }
}