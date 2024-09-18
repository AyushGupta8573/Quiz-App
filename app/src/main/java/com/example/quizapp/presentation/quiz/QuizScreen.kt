package com.example.quizapp.presentation.quiz

import android.provider.SyncStateContract.Constants
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.R
import com.example.quizapp.presentation.common.QuizAppBar
import com.example.quizapp.presentation.util.Dimens
import com.example.quizapp.presentation.util.Dimens.LargeSpacerHeight
import com.example.quizapp.presentation.util.Dimens.MediumCornerRadius
import com.example.quizapp.presentation.util.Dimens.SmallSpacerHeight
import com.example.quizapp.presentation.util.Dimens.VerySmallViewHeight

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
        }
    }
}

//@Preview
//@Composable
//private fun QuizScreenPreview() {
 //   QuizScreen(numOfQuiz = 12, quizCategory = "GK", quizDifficulty = "Easy")
//}