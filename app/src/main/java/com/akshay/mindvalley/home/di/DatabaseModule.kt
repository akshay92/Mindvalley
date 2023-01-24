package com.akshay.mindvalley.home.di

import android.content.Context
import androidx.room.Room
import com.akshay.mindvalley.home.data.local.db.MindValleyDatabase
import com.akshay.mindvalley.home.data.local.db.dao.CategoryDao
import com.akshay.mindvalley.home.data.local.db.dao.ChannelDao
import com.akshay.mindvalley.home.data.local.db.dao.LatestMediaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMindValleyDatabase(@ApplicationContext application: Context): MindValleyDatabase {
        return Room.databaseBuilder(
            application,
            MindValleyDatabase::class.java,
            "mindvalley.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideChannelDao(appDatabase: MindValleyDatabase): ChannelDao =
        appDatabase.channelDao()


    @Singleton
    @Provides
    fun provideLatestMediaDao(appDatabase: MindValleyDatabase): LatestMediaDao =
        appDatabase.latestMediaDao()

    @Singleton
    @Provides
    fun provideCategoryDao(appDatabase: MindValleyDatabase): CategoryDao =
        appDatabase.categoryDao()

}