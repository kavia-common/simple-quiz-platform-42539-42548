androidApplication {
    namespace = "org.example.app"

    // Enable Jetpack Compose in the declarative DSL
    compose {
        enabled = true
    }

    dependencies {
        // Compose BOM to align versions
        implementation(platform("androidx.compose:compose-bom:2024.10.01"))

        // Core Compose/Material3
        implementation("androidx.activity:activity-compose:1.9.3")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.compose.ui:ui-tooling-preview")

        // Navigation for Compose
        implementation("androidx.navigation:navigation-compose:2.8.3")
    }

    // Debug-only dependencies using declarative DSL
    buildTypes {
        debug {
            dependencies {
                implementation("androidx.compose.ui:ui-tooling")
            }
        }
    }
}
