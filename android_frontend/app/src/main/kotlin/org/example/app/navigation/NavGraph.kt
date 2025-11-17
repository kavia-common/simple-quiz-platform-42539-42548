package org.example.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.app.ui.screens.QuizListScreen
import org.example.app.ui.screens.QuizScreen
import org.example.app.ui.screens.ResultsScreen

/**
 * PUBLIC_INTERFACE
 * QuizNavHost
 *
 * Composable navigation host for the quiz application.
 * Routes:
 * - list
 * - quiz/{quizId}
 * - results/{quizId}/{score}/{total}
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuizNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            QuizListScreen(
                onStartQuiz = { quizId ->
                    navController.navigate("quiz/$quizId")
                }
            )
        }

        composable(
            route = "quiz/{quizId}",
            arguments = listOf(navArgument("quizId") { type = NavType.StringType })
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId") ?: return@composable
            QuizScreen(
                quizId = quizId,
                onSubmit = { score, total ->
                    navController.navigate("results/$quizId/$score/$total") {
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "results/{quizId}/{score}/{total}",
            arguments = listOf(
                navArgument("quizId") { type = NavType.StringType },
                navArgument("score") { type = NavType.IntType },
                navArgument("total") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId") ?: ""
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val total = backStackEntry.arguments?.getInt("total") ?: 0
            ResultsScreen(
                quizId = quizId,
                score = score,
                total = total,
                onRetry = {
                    // Restart same quiz
                    navController.popBackStack(route = "list", inclusive = false)
                    navController.navigate("quiz/$quizId")
                },
                onBackToList = {
                    navController.popBackStack(route = "list", inclusive = false)
                }
            )
        }
    }
}
