package com.taetae98.coronawidget.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taetae98.coronawidget.DATABASE_NAME
import com.taetae98.coronawidget.dto.KoreaCoronaWidgetInformation

@Database(entities = [KoreaCoronaWidgetInformation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }

    abstract fun koreaCoronaWidgetInformation(): KoreaCoronaWidgetInformationDao
}