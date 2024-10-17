package com.example.quizapp.presentation.main_activity

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.quizapp.R
import com.example.quizapp.domain.repository.QuizRepository
import com.example.quizapp.presentation.nav_graph.SetNavGraph
import com.example.quizapp.ui.theme.QuizAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var quizRepositry: QuizRepository
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        actionBar?.hide()
        installSplashScreen()
        setContent {
            QuizAppTheme {
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(id = R.color.mid_night_blue)),
                    contentAlignment = Alignment.Center
                ){
                    SetNavGraph()
                }
            }
        }
    }
}
