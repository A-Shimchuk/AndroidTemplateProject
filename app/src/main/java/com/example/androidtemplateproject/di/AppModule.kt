package com.example.androidtemplateproject.di

import com.example.androidtemplateproject.screens.appList.data.AppRepositoryImpl
import com.example.androidtemplateproject.screens.appList.data.ApplicationMapper
import com.example.androidtemplateproject.screens.appList.data.network.ApplicationsApiService
import com.example.androidtemplateproject.screens.appList.domain.AppRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Binds
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .build()
        }

        @Provides
        @Singleton
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApplicationsApiService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @Provides
        @Singleton
        fun provideApplicationsApiService(retrofit: Retrofit): ApplicationsApiService {
            return retrofit.create(ApplicationsApiService::class.java)
        }
    }
}
