package com.example.quizapp.presentation.quiz

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.common.Resource
import com.example.quizapp.domain.model.Quiz
import com.example.quizapp.domain.usecases.GetQuizzesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuizzesUseCases: GetQuizzesUseCases): ViewModel() {

    private val _quizList = MutableStateFlow(StateQuizScreen())
    val quizList = _quizList

    fun onEvent(event: EventQuizScreen){
        when(event){
            is EventQuizScreen.GetQuizzes -> {
                getQuizzes(event.numOfQuizzes, event.category, event.difficulty, event.type)
            }
            else -> {}
        }
    }

    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String){
        viewModelScope.launch{
            getQuizzesUseCases(amount, category, difficulty, type).collect{resource ->
                when(resource){
                    is Resource.Loading -> {
                        Log.d("quiz", "loading")
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }
                    is Resource.Success -> {
                        Log.d("quiz", "success")
                        val listOfQuizState : List<QuizState> = getListOfQuizState(resource.data)
                        _quizList.value = StateQuizScreen(quizState = listOfQuizState)
                    }
                    is Resource.Error -> {
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }

    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {
        val listOfQuizState = mutableListOf<QuizState>()
        for (quiz in data!!){

            val shuffledOptions = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }

            listOfQuizState.add(QuizState(quiz,shuffledOptions, -1))
        }
        return listOfQuizState
    }
}