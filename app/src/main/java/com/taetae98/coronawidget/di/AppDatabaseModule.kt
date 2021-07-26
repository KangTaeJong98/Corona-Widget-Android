package com.taetae98.coronawidget.di

import android.content.Context
import com.taetae98.coronawidget.room.AppDatabase
import com.taetae98.coronawidget.room.KoreaCoronaWidgetInformationDao
import com.taetae98.coronawidget.room.LocalCoronaWidgetInformationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {
    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providesKoreaCoronaWidgetInformationDao(appDatabase: AppDatabase): KoreaCoronaWidgetInformationDao {
        return appDatabase.koreaCoronaWidgetInformation()
    }

    @Singleton
    @Provides
    fun providesLocalCoronaWidgetInformationDao(appDatabase: AppDatabase): LocalCoronaWidgetInformationDao {
        return appDatabase.localCoronaWidgetInformation()
    }
}