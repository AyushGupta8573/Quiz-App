package com.example.quizapp.data.repository

import android.util.Log
import com.example.quizapp.data.retrofit.QuizApi
import com.example.quizapp.domain.model.Quiz
import com.example.quizapp.domain.repository.QuizRepository

class QuizRepositryImpl (
    private val quizApi: QuizApi
): QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        val rsp =  quizApi.getQuizzes(amount, category, difficulty, type).results
        Log.d("quiz", rsp.toString())
        return rsp
    }
}