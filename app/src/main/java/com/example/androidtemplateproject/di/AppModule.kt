package com.example.androidtemplateproject.di

import com.example.androidtemplateproject.screens.appList.data.AppRepositoryImpl
import com.example.androidtemplateproject.screens.appList.data.ApplicationMapper
import com.example.androidtemplateproject.screens.appList.data.ApplicationsAPI
import com.example.androidtemplateproject.screens.appList.domain.AppRepository
import dagger.Module
import dagger.Binds
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    companion object {
        @Provides
        @Singleton
        fun provideApplicationMapper(): ApplicationMapper = ApplicationMapper()

        @Provides
        @Singleton
        fun provideApplicationsAPI(): ApplicationsAPI = ApplicationsAPI()
    }
}
