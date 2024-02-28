package com.ahmedkenawy.footballleague.di.modules.remote

import com.ahmedkenawy.footballleague.features.list.data.remote.CompetitionsApi
import com.ahmedkenawy.footballleague.network.NetworkHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.ahmedkenawy.footballleague.BuildConfig
import com.ahmedkenawy.footballleague.network.NetworkConnectionInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder



/**
 * Dagger module providing network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides Gson instance for JSON serialization/deserialization.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    /**
     * Provides GsonConverterFactory for Retrofit.
     */
    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    /**
     * Provides HttpLoggingInterceptor for logging HTTP request/response details.
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * Provides OkHttpClient for making HTTP requests.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        connectivityInterceptor: NetworkConnectionInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        networkHeaderInterceptor: NetworkHeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkHeaderInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides Retrofit instance for API calls.
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    /**
     * Provides API service interface implementation.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CompetitionsApi {
        return retrofit.create(CompetitionsApi::class.java)
    }
}