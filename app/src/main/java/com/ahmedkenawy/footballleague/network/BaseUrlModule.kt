package com.ahmedkenawy.footballleague.network

import com.ahmedkenawy.footballleague.BuildConfig
import com.ahmedkenawy.footballleague.utils.Constants.Network.BASE_URL_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing the base URL used for network requests.
 */
@Module
@InstallIn(SingletonComponent::class)
class BaseUrlModule {

    /**
     * Provides the base URL used for network requests.
     *
     * @return The base URL string.
     */
    @Provides
    @Singleton
    @Named(BASE_URL_KEY)
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}