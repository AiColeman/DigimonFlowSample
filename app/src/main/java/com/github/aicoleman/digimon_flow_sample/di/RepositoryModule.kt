package com.github.aicoleman.digimon_flow_sample.di

import com.github.aicoleman.digimon_flow_sample.data.remote.DigimonService
import com.github.aicoleman.digimon_flow_sample.repository.DigimonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @ExperimentalCoroutinesApi
    @Provides
    @ActivityRetainedScoped
    fun provideDigimonRepository(
        digimonService: DigimonService
    ): DigimonRepository {
        return DigimonRepository(digimonService)
    }
}