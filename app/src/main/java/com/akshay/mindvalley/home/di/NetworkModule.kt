package com.akshay.mindvalley.home.di

import com.akshay.mindvalley.home.data.remote.CategoryService
import com.akshay.mindvalley.home.data.remote.ChannelService
import com.akshay.mindvalley.home.data.remote.NewEpisodeService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val url  = "https://pastebin.com/raw/"

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideChannelService(
        gson: Gson
    ): ChannelService {
        return Retrofit.Builder()
            .baseUrl(url)  // We should get it from build config
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ChannelService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewEpisodeService(
        gson: Gson
    ): NewEpisodeService {
        return Retrofit.Builder()
            .baseUrl(url)  // We should get it from build config
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(NewEpisodeService::class.java)
    }


    @Singleton
    @Provides
    fun provideCategoryService(
        gson: Gson
    ): CategoryService {
        return Retrofit.Builder()
            .baseUrl(url)  // We should get it from build config
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(CategoryService::class.java)
    }
}