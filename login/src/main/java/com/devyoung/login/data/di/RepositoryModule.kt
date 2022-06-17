package com.devyoung.login.data.di

import com.devyoung.login.data.dataSource.RemoteDataSourceImpl
import com.devyoung.login.data.repository.LoginRepositoryImpl
import com.devyoung.login.domain.dataSource.RemoteDataSource
import com.devyoung.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(remoteDataSource: RemoteDataSource) : LoginRepository {
        return LoginRepositoryImpl(remoteDataSource)
    }

}