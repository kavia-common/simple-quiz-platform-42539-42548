package org.example.app.model

/**
 * Core data models for quiz domain.
 */

// PUBLIC_INTERFACE
data class Choice(
    /** Display text for the choice */
    val text: String,
    /** Whether this choice is correct */
    val isCorrect: Boolean
)

// PUBLIC_INTERFACE
data class Question(
    /** The question prompt */
    val prompt: String,
    /** List of 4 choices (single correct) */
    val choices: List<Choice>
)

// PUBLIC_INTERFACE
data class Quiz(
    /** Quiz identifier */
    val id: String,
    /** Quiz title */
    val title: String,
    /** Short description */
    val description: String,
    /** Fixed list of questions */
    val questions: List<Question>
)
