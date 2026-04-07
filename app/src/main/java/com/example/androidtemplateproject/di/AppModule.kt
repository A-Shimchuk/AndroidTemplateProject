package com.example.androidtemplateproject.di

import com.example.androidtemplateproject.screens.appList.data.AppRepositoryImpl
import com.example.androidtemplateproject.screens.appList.data.ApplicationMapper
import com.example.androidtemplateproject.screens.appList.data.ApplicationsApiService
import com.example.androidtemplateproject.screens.appList.domain.AppRepository
import com.example.androidtemplateproject.screens.detailScreen.data.DetailApplicationMapper
import com.example.androidtemplateproject.screens.detailScreen.data.DetailRepositoryImpl
import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDatabase
import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDetailsDao
import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDetailsEntityMapper
import com.example.androidtemplateproject.screens.detailScreen.data.network.DetailApplicationsApiService
import com.example.androidtemplateproject.screens.detailScreen.domain.DetailRepository
import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Binds
    @Singleton
    abstract fun bindDetailRepository(impl: DetailRepositoryImpl): DetailRepository

    companion object {
        @Provides
        @Singleton
        fun provideApplicationMapper(): ApplicationMapper = ApplicationMapper()

        @Provides
        @Singleton
        fun provideDetailApplicationMapper(): DetailApplicationMapper = DetailApplicationMapper()

        @Provides
        @Singleton
        fun provideAppDetailsEntityMapper(): AppDetailsEntityMapper = AppDetailsEntityMapper()

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            ).build()
        }

        @Provides
        @Singleton
        fun provideAppDetailsDao(database: AppDatabase): AppDetailsDao {
            return database.appDetailsDao()
        }
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

        @Provides
        @Singleton
        fun provideDetailApplicationsApiService(retrofit: Retrofit): DetailApplicationsApiService {
            return retrofit.create(DetailApplicationsApiService::class.java)
        }
    }
}
