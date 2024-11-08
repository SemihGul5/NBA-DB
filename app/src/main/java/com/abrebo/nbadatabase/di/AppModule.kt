package com.abrebo.nbadatabase.di

import android.content.Context
import com.abrebo.nbadatabase.data.datasource.DataSource
import com.abrebo.nbadatabase.data.repo.Repository
import com.abrebo.nbadatabase.retrofit.ApiUtils
import com.abrebo.nbadatabase.retrofit.RosterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDataSource(rosterDao: RosterDao):DataSource{
        return DataSource(rosterDao)
    }

    @Provides
    @Singleton
    fun provideRepository(dataSource: DataSource,
                          @ApplicationContext context: Context):Repository{
        return Repository(dataSource,context)
    }

    @Provides
    @Singleton
    fun provideTeamDao():RosterDao{
        return ApiUtils.getRosterDao()
    }

}