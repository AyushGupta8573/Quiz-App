package com.example.quizapp.presentation.nav_graph

import android.text.TextUtils.replace
import com.example.quizapp.presentation.home.EventHomeScreen

const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"

sealed class Routes(val route : String) {
    object HomeScreen : Routes("home_screen")

    object QuizScreen : Routes("quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"){

        fun passQuizParams(numberOfQuizzes: Int, category: String, difficulty: String, type: String): String {

            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_NUMBER}",
                    newValue = numberOfQuizzes.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_CATEGORY}",
                    newValue = category.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}",
                    newValue = difficulty.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_TYPE}",
                    newValue = type.toString()
                )
        }
    }

    object ScoreScreen : Routes("score_screen")
}