package com.devyoung.login.data.di

import com.devyoung.login.data.dataSource.RemoteDataSourceImpl
import com.devyoung.login.domain.dataSource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource() : RemoteDataSource {
        return RemoteDataSourceImpl()
    }

}