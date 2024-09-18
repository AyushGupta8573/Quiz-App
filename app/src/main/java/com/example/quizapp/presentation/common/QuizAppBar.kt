package com.example.quizapp.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.quizapp.R
import com.example.quizapp.presentation.home.EventHomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAppBar(
    modifier: Modifier = Modifier,
    quizCategory: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.dark_slate_blue),
            actionIconContentColor = colorResource(id = R.color.blue_gray),
            navigationIconContentColor = colorResource(id = R.color.blue_gray)
        ),
        title = {
            Text(
                text = quizCategory,
                color = colorResource(id = R.color.blue_gray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painterResource(id = R.drawable.keyboard_backspace_24dp), contentDescription = "")
            }
        },
    )
}