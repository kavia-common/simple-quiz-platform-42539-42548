package org.example.app.data

import org.example.app.model.Choice
import org.example.app.model.Question
import org.example.app.model.Quiz

/**
 * In-memory static repository of quizzes.
 * No networking or persistence. All data is local and ephemeral.
 */
object InMemoryQuizRepository {

    // PUBLIC_INTERFACE
    fun getQuizzes(): List<Quiz> = sampleQuizzes

    // PUBLIC_INTERFACE
    fun getQuizById(id: String): Quiz? = sampleQuizzes.find { it.id == id }

    private val sampleQuizzes: List<Quiz> by lazy {
        listOf(
            Quiz(
                id = "basics_android",
                title = "Android Basics",
                description = "Test your knowledge of Android fundamentals.",
                questions = listOf(
                    Question(
                        prompt = "Which language is primarily used with Jetpack Compose?",
                        choices = listOf(
                            Choice("Java", false),
                            Choice("Kotlin", true),
                            Choice("Swift", false),
                            Choice("Dart", false),
                        )
                    ),
                    Question(
                        prompt = "What is the recommended architecture for modern Android apps?",
                        choices = listOf(
                            Choice("MVC", false),
                            Choice("Singletons Everywhere", false),
                            Choice("MVVM with unidirectional data flow", true),
                            Choice("Global static state", false),
                        )
                    ),
                    Question(
                        prompt = "Which component handles navigation in Compose?",
                        choices = listOf(
                            Choice("NavHost & NavController", true),
                            Choice("Fragments only", false),
                            Choice("Activities only", false),
                            Choice("XML Intents only", false),
                        )
                    ),
                    Question(
                        prompt = "What Material version is recommended in Compose?",
                        choices = listOf(
                            Choice("Material 1", false),
                            Choice("Material 2", false),
                            Choice("Material 3", true),
                            Choice("Material Classic", false),
                        )
                    ),
                    Question(
                        prompt = "Which tool previews Compose UI?",
                        choices = listOf(
                            Choice("Android Studio Preview", true),
                            Choice("ADB only", false),
                            Choice("Gradle CLI only", false),
                            Choice("No preview available", false),
                        )
                    )
                )
            ),
            Quiz(
                id = "kotlin_fundamentals",
                title = "Kotlin Fundamentals",
                description = "A quick check of Kotlin language basics.",
                questions = listOf(
                    Question(
                        prompt = "Which keyword defines an immutable variable?",
                        choices = listOf(
                            Choice("var", false),
                            Choice("val", true),
                            Choice("let", false),
                            Choice("const", false),
                        )
                    ),
                    Question(
                        prompt = "A Kotlin data class primarily provides:",
                        choices = listOf(
                            Choice("Auto-generated equals/hashCode/toString", true),
                            Choice("Multiple inheritance", false),
                            Choice("Global state", false),
                            Choice("Unsafe nulls", false),
                        )
                    ),
                    Question(
                        prompt = "Which is a null-safe call operator?",
                        choices = listOf(
                            Choice("?.", true),
                            Choice("!!", false),
                            Choice("::", false),
                            Choice("?:", false),
                        )
                    )
                ).plus(
                    listOf(
                        Question(
                            prompt = "Which collection is immutable by default?",
                            choices = listOf(
                                Choice("mutableListOf()", false),
                                Choice("listOf()", true),
                                Choice("arrayListOf()", false),
                                Choice("hashMapOf()", false),
                            )
                        ),
                        Question(
                            prompt = "What does 'when' represent?",
                            choices = listOf(
                                Choice("A powerful switch-like expression", true),
                                Choice("A loop", false),
                                Choice("A keyword to declare classes", false),
                                Choice("An annotation", false),
                            )
                        )
                    )
                )
            ),
            Quiz(
                id = "general_knowledge",
                title = "General Knowledge",
                description = "Fun general knowledge questions.",
                questions = listOf(
                    Question(
                        prompt = "What is the capital of France?",
                        choices = listOf(
                            Choice("Paris", true),
                            Choice("Berlin", false),
                            Choice("Rome", false),
                            Choice("Madrid", false),
                        )
                    ),
                    Question(
                        prompt = "Which ocean is the largest?",
                        choices = listOf(
                            Choice("Atlantic", false),
                            Choice("Indian", false),
                            Choice("Pacific", true),
                            Choice("Arctic", false),
                        )
                    ),
                    Question(
                        prompt = "Which planet is known as the Red Planet?",
                        choices = listOf(
                            Choice("Mars", true),
                            Choice("Venus", false),
                            Choice("Jupiter", false),
                            Choice("Mercury", false),
                        )
                    ),
                    Question(
                        prompt = "The chemical symbol for water is:",
                        choices = listOf(
                            Choice("H2O", true),
                            Choice("O2", false),
                            Choice("CO2", false),
                            Choice("NaCl", false),
                        )
                    ),
                    Question(
                        prompt = "How many continents are there?",
                        choices = listOf(
                            Choice("7", true),
                            Choice("5", false),
                            Choice("6", false),
                            Choice("8", false),
                        )
                    )
                )
            )
        )
    }
}
