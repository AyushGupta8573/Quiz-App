package com.example.quizapp.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.quizapp.presentation.common.AppDropDownMenu
import com.example.quizapp.presentation.common.ButtonBox
import com.example.quizapp.presentation.home.component.HomeHeader
import com.example.quizapp.presentation.nav_graph.Routes
import com.example.quizapp.presentation.util.Constants
import com.example.quizapp.presentation.util.Constants.difficulty
import com.example.quizapp.presentation.util.Dimens
import com.example.quizapp.presentation.util.Dimens.MediumPadding
import com.example.quizapp.presentation.util.Dimens.MediumSpacerHeight
import com.example.quizapp.presentation.util.Dimens.SmallSpacerHeight

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state : StateHomeScreen,
    event : (EventHomeScreen) -> Unit,
    navController: NavController
) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        HomeHeader()

        Spacer(modifier = modifier.height(MediumSpacerHeight))
        AppDropDownMenu(menuName = "Number of Questions:", menuList = Constants.numbersAsString, text = state.numberOfQuiz.toString(), onDropDownClick = {event(EventHomeScreen.SetNumberOfQuizzes(it.toInt()))})

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Category:", menuList = Constants.categories, text = state.category , onDropDownClick = {event(EventHomeScreen.SetQuizCategory(it))})

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Difficulty:", menuList = difficulty, text = state.difficulty , onDropDownClick = {event(EventHomeScreen.SetQuizDifficulty(it))})

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Type:", menuList = Constants.type, text = state.type, onDropDownClick = {event(EventHomeScreen.SetQuizType(it))})

        Spacer(modifier = modifier.height(MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = MediumPadding){
            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(state.numberOfQuiz, state.category, state.difficulty, state.type)
            )
        }
    }
}
