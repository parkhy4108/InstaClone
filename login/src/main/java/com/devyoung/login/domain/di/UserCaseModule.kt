package com.devyoung.login.domain.di

import com.devyoung.login.domain.repository.LoginRepository
import com.devyoung.login.domain.usecase.LoginUseCases
import com.devyoung.login.domain.usecase.UserLogin
import com.devyoung.login.domain.usecase.UserSignUp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserCaseModule {

    @Provides
    @Singleton
    fun provideUseCases(
        repository: LoginRepository
    ) : LoginUseCases {
        return LoginUseCases(
            userLogin = UserLogin(repository),
            userSignUp = UserSignUp(repository)
        )
    }


}












