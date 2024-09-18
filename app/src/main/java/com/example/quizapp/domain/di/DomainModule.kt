package com.example.quizapp.domain.di

import com.example.quizapp.data.repository.QuizRepositryImpl
import com.example.quizapp.data.retrofit.QuizApi
import com.example.quizapp.domain.repository.QuizRepositry
import com.example.quizapp.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
 fun provideGetQuizzesUseCases(quizRepositry: QuizRepositry) : GetQuizzesUseCases {
     return GetQuizzesUseCases(quizRepositry)

 }
}