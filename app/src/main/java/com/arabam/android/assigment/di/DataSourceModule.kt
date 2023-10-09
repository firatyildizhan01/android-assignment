package com.arabam.android.assigment.di

import com.arabam.android.assigment.data.source.ApiService
import com.arabam.android.assigment.data.source.RemoteDataSourceImpl
import com.arabam.android.assigment.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(
        apiService: ApiService
    ) : RemoteDataSource = RemoteDataSourceImpl(apiService)
}
