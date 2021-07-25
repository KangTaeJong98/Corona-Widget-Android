package com.taetae98.coronawidget.repository

import com.taetae98.coronawidget.dto.KoreaCoronaWidgetInformation
import com.taetae98.coronawidget.room.KoreaCoronaWidgetInformationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetRepository @Inject constructor(
    private val koreaCoronaWidgetInformationDao: KoreaCoronaWidgetInformationDao
) {
    suspend fun selectByIdKoreaCoronaWidgetInformation(id: Int): KoreaCoronaWidgetInformation? {
        return koreaCoronaWidgetInformationDao.selectById(id)
    }

    suspend fun insert(information: KoreaCoronaWidgetInformation): Long {
        return koreaCoronaWidgetInformationDao.insert(information)
    }

    suspend fun deleteById(ids: IntArray): Int {
        return koreaCoronaWidgetInformationDao.deleteById(ids)
    }
}