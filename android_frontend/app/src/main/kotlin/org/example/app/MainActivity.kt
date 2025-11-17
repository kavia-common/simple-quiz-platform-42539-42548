package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import org.example.app.navigation.QuizNavHost
import org.example.app.ui.theme.OceanProfessionalTheme

/**
 * PUBLIC_INTERFACE
 * MainActivity
 *
 * Entry point Activity hosting a single-activity Compose app.
 * Sets the Ocean Professional theme and provides the NavHost for the quiz flow.
 */
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Edge-to-edge content
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            OceanProfessionalTheme {
                SetSystemBarsColor()
                Surface(color = MaterialTheme.colorScheme.background) {
                    QuizNavHost()
                }
            }
        }
    }
}

@Composable
private fun SetSystemBarsColor() {
    // set system bar colors to match background for clean look
    val color = MaterialTheme.colorScheme.background
    try {
        val window = androidx.compose.ui.platform.LocalView.current.context
            .let { (it as? ComponentActivity)?.window }
        window?.statusBarColor = color.toArgb()
        window?.navigationBarColor = color.toArgb()
        val isDark = isSystemInDarkTheme()
        WindowCompat.getInsetsController(window!!, window.decorView).isAppearanceLightStatusBars = !isDark
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightNavigationBars = !isDark
    } catch (_: Throwable) {
        // no-op if not available
    }
}
