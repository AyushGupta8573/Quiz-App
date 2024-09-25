package com.example.quizapp.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizapp.presentation.home.HomeScreen
import com.example.quizapp.presentation.home.HomeScreenViewModel
import com.example.quizapp.presentation.quiz.QuizScreen
import com.example.quizapp.presentation.quiz.QuizViewModel

@Composable
fun SetNavGraph(
    modifier: Modifier = Modifier
) {

    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route){
        composable(route = Routes.HomeScreen.route){
            val viewModel : HomeScreenViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(
                state = state,
                event = viewModel::onEvent,
                navController = navController
            )
        }

        composable(
            route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER){type = NavType.IntType},
                navArgument(ARG_KEY_QUIZ_CATEGORY){type = NavType.StringType},
                navArgument(ARG_KEY_QUIZ_DIFFICULTY){type = NavType.StringType},
                navArgument(ARG_KEY_QUIZ_TYPE){type = NavType.StringType}
            )
        ){
            val numberOfQuizzes = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER)
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE)

            val quizViewModel : QuizViewModel = hiltViewModel()
            val state by quizViewModel.quizList.collectAsState()
            QuizScreen(
                numOfQuiz = numberOfQuizzes!!,
                quizCategory = category!!,
                quizDifficulty = difficulty!!,
                quizType = type!!,
                event = quizViewModel::onEvent,
                state = state
                )
        }
    }
}