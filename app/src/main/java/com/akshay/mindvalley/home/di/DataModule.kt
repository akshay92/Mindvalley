package com.akshay.mindvalley.home.di

import com.akshay.mindvalley.home.data.repository.CategoryRepositoryImpl
import com.akshay.mindvalley.home.data.repository.ChannelRepositoryImpl
import com.akshay.mindvalley.home.data.repository.NewEpisodeRepositoryImpl
import com.akshay.mindvalley.home.data.source.*
import com.akshay.mindvalley.home.domain.repository.CategoryRepository
import com.akshay.mindvalley.home.domain.repository.ChannelRepository
import com.akshay.mindvalley.home.domain.repository.NewEpisodeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
abstract class DataModule {

    @Binds
    abstract fun bindCategoryRepository(resp: CategoryRepositoryImpl): CategoryRepository

    @Binds
    abstract fun bindChannelRepository(resp: ChannelRepositoryImpl): ChannelRepository

    @Binds
    abstract fun bindNewEpisodeRepository(resp: NewEpisodeRepositoryImpl): NewEpisodeRepository

    @Binds
    abstract fun bindLocalCategoryDataSource(resp: LocalCategoryDataSourceImp): LocalCategoryDataSource

    @Binds
    abstract fun bindLocalChannelDataSourceImp(resp: LocalChannelDataSourceImp): LocalChannelDataSource

    @Binds
    abstract fun bindLocalLatestMediaDataSource(resp: LocalLatestMediaDataSourceImp): LocalLatestMediaDataSource

    @Binds
    abstract fun bindRemoteLatestMediaDataSource(resp: RemoteLatestMediaDataSourceImp): RemoteLatestMediaDataSource

    @Binds
    abstract fun bindRemoteCategoryDataSource(resp: RemoteCategoryDataSourceImp): RemoteCategoryDataSource

    @Binds
    abstract fun bindRemoteChannelDataSource(resp: RemoteChannelDataSourceImp): RemoteChannelDataSource

}

