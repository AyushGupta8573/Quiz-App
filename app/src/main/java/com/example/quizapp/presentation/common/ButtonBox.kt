package com.example.quizapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.presentation.util.Dimens

@Composable
fun ButtonBox(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp,
    onButtonClick: ()-> Unit
) {
    Box(modifier = Modifier
        .padding(padding)
        .clickable { onButtonClick() }
        .fillMaxWidth()
        .height(Dimens.MediumBoxHeight)
        .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
        .background(colorResource(id = R.color.blue_gray)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = Dimens.MediumTextSize,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

//@Preview
//@Composable
//private fun ButtonBoxPreview() {
  //  ButtonBox(text = "Generate Quiz", padding = 20.dp, onButtonClick = {})
//}