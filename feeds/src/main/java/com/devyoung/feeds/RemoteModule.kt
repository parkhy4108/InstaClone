package com.devyoung.feeds


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