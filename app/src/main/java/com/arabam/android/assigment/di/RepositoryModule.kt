package com.arabam.android.assigment.di

import com.arabam.android.assigment.data.repository.CarRepositoryImpl
import com.arabam.android.assigment.domain.repository.CarRepository
import com.arabam.android.assigment.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesCarRepository(
        remoteDataSource: RemoteDataSource
    ) : CarRepository = CarRepositoryImpl(remoteDataSource)
}
