package com.zeynelerdi.deezer.di

import com.zeynelerdi.deezer.domain.local.DeezerDao
import com.zeynelerdi.deezer.domain.network.DeezerClient
import com.zeynelerdi.deezer.repository.DeezerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ): DeezerRepository = DeezerRepository(deezerClient,deezerDao)


}