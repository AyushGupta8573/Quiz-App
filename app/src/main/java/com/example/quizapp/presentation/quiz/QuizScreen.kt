package com.example.quizapp.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.R
import com.example.quizapp.presentation.common.ButtonBox
import com.example.quizapp.presentation.common.QuizAppBar
import com.example.quizapp.presentation.quiz.component.QuizInterface
import com.example.quizapp.presentation.quiz.component.ShimmerEffectQuizInterface
import com.example.quizapp.presentation.util.Dimens
import com.example.quizapp.presentation.util.Dimens.LargeSpacerHeight
import com.example.quizapp.presentation.util.Dimens.MediumCornerRadius
import com.example.quizapp.presentation.util.Dimens.MediumPadding
import com.example.quizapp.presentation.util.Dimens.SmallSpacerHeight
import com.example.quizapp.presentation.util.Dimens.VerySmallViewHeight
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen
) {

    LaunchedEffect(key1 = Unit){
        val difficulty = when(quizDifficulty){

            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }
        val type = when(quizType){
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        event(EventQuizScreen.GetQuizzes(numOfQuiz, com.example.quizapp.presentation.util.Constants.categoriesMap[quizCategory]!!, quizDifficulty, type))
    }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        QuizAppBar(quizCategory = quizCategory) {

        }

        Column(
            modifier = Modifier
                .padding(Dimens.VerySmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.height(LargeSpacerHeight))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Question: $numOfQuiz",
                    color = colorResource(id = R.color.blue_gray)
                )
                Text(
                    text = quizDifficulty,
                    color = colorResource(id = R.color.blue_gray)
                )
            }
            Spacer(modifier = modifier.height(SmallSpacerHeight))

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(VerySmallViewHeight)
                    .clip(RoundedCornerShape(MediumCornerRadius))
                    .background(colorResource(id = R.color.blue_gray)),
            )
            Spacer(modifier = modifier.height(LargeSpacerHeight))

            if (quizFetched(state)) {
                val pagerState = rememberPagerState() { state.quizState.size }

                HorizontalPager(state = pagerState) { index ->
                    QuizInterface(
                        modifier = Modifier
                            .weight(1f),
                        onOptionSelected = {},
                        qNumber = index + 1,
                        quizState = state.quizState[index]
                    )
                }

                val buttonText by remember {
                    derivedStateOf {
                        when (pagerState.currentPage) {
                            0 -> {
                                listOf("", "Next")
                            }

                            state.quizState.size - 1 -> {
                                listOf("Previous", "Submit")
                            }

                            else -> {
                                listOf("Previous", "Next")
                            }
                        }
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = MediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()
                    if (buttonText[0].isNotEmpty()) {
                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize
                        ) {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                        }
                    }
                    else{
                        ButtonBox(
                            text = "",
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize,
                            borderColor = colorResource(id = R.color.mid_night_blue),
                            containerColor = colorResource(id = R.color.mid_night_blue)
                        ) {
                        }
                    }

                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        borderColor = colorResource(id = R.color.orange),
                        containerColor = if (pagerState.currentPage == state.quizState.size -1) colorResource(id = R.color.orange)else colorResource(id = R.color.dark_slate_blue),
                        textColor = colorResource(id = R.color.white),
                        fraction = 1f,
                        fontSize = Dimens.SmallTextSize
                    ) {
                        if (pagerState.currentPage == state.quizState.size - 1) {
                            //TODO
                        } else {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun quizFetched(state: StateQuizScreen): Boolean {
    return when {
        state.isLoading -> {
            ShimmerEffectQuizInterface()
            false
        }

        state.quizState?.isNotEmpty() == true -> {
            true
        }

        else -> {
            Text(text = state.error.toString(), color = colorResource(id = R.color.white))
            false
        }
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    QuizScreen(
        numOfQuiz = 12,
        quizCategory = "GK",
        quizDifficulty = "Easy",
        quizType = "Multiple Choice",
        event = {},
        state = StateQuizScreen()
    )
}