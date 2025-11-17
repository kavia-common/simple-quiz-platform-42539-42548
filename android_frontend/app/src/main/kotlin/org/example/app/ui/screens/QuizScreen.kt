package org.example.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.app.data.InMemoryQuizRepository
import org.example.app.model.Question

/**
 * PUBLIC_INTERFACE
 * QuizScreen
 *
 * Displays quiz questions in a "paged" experience with Previous/Next actions.
 * On the last question, a Submit button computes the score and navigates to results.
 *
 * @param quizId ID of the quiz to present
 * @param onSubmit callback delivering score and total when submit is triggered
 * @param onBack navigate back callback
 */
@Composable
fun QuizScreen(
    quizId: String,
    onSubmit: (score: Int, total: Int) -> Unit,
    onBack: () -> Unit
) {
    val quiz = remember(quizId) { InMemoryQuizRepository.getQuizById(quizId) }
    if (quiz == null) {
        // fallback UI
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Quiz not found", color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(8.dp))
                OutlinedButton(onClick = onBack) { Text("Back") }
            }
        }
        return
    }

    val total = quiz.questions.size
    var page by remember { mutableStateOf(0) }
    val selections = remember { mutableStateListOf<Int?>(*Array(total) { null }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onBack) {
                Text("Back")
            }
            Text(
                text = quiz.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "${page + 1}/$total",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }

        Spacer(Modifier.height(12.dp))
        Divider()

        Spacer(Modifier.height(12.dp))

        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(250)),
            exit = fadeOut(animationSpec = tween(250))
        ) {
            QuestionCard(
                question = quiz.questions[page],
                selectedIndex = selections[page],
                onSelect = { idx ->
                    selections[page] = idx
                }
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                enabled = page > 0,
                onClick = { if (page > 0) page -= 1 }
            ) { Text("Previous") }

            if (page < total - 1) {
                Button(
                    enabled = selections[page] != null,
                    onClick = { if (page < total - 1) page += 1 }
                ) { Text("Next") }
            } else {
                Button(
                    enabled = selections.all { it != null },
                    onClick = {
                        // compute score
                        val score = quiz.questions.indices.count { i ->
                            val selected = selections[i]
                            val correctIndex = quiz.questions[i].choices.indexOfFirst { it.isCorrect }
                            selected == correctIndex
                        }
                        onSubmit(score, total)
                    }
                ) { Text("Submit") }
            }
        }
    }
}

@Composable
private fun QuestionCard(
    question: Question,
    selectedIndex: Int?,
    onSelect: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(
                text = question.prompt,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(12.dp))

            question.choices.forEachIndexed { index, choice ->
                val isSelected = selectedIndex == index
                val background =
                    if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                    else MaterialTheme.colorScheme.surface
                val borderColor =
                    if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                val textColor =
                    if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface

                FilledTonalButton(
                    onClick = { onSelect(index) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .height(52.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = background,
                        contentColor = textColor
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(choice.text)
                }
            }
        }
    }
}
