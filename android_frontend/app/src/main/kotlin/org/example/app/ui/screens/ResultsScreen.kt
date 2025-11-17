package org.example.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.app.data.InMemoryQuizRepository

/**
 * PUBLIC_INTERFACE
 * ResultsScreen
 *
 * Shows total score and percentage, and allows retrying the quiz or returning to list.
 *
 * @param quizId ID of the quiz taken (used for retry label)
 * @param score number of correct answers
 * @param total total number of questions
 * @param onRetry invoked to retry the same quiz
 * @param onBackToList invoked to navigate to the quiz list
 */
@Composable
fun ResultsScreen(
    quizId: String,
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onBackToList: () -> Unit
) {
    val quiz = InMemoryQuizRepository.getQuizById(quizId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Results",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(16.dp))

        AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, shape = MaterialTheme.shapes.large),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(3.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val pct = if (total > 0) (score * 100f / total) else 0f

                    Text(
                        text = "Score: $score / $total",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Percentage: ${"%.1f".format(pct)}%",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(Modifier.height(16.dp))
                    Divider()
                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = quiz?.title ?: "Quiz",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        Button(onClick = onRetry, modifier = Modifier.fillMaxWidth()) {
            Text("Retry")
        }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onBackToList, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Quiz List")
        }
    }
}
