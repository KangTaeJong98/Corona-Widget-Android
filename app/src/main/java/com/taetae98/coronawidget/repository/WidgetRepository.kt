package com.taetae98.coronawidget.repository

import com.taetae98.coronawidget.dto.KoreaCoronaWidgetInformation
import com.taetae98.coronawidget.dto.LocalCoronaWidgetInformation
import com.taetae98.coronawidget.room.KoreaCoronaWidgetInformationDao
import com.taetae98.coronawidget.room.LocalCoronaWidgetInformationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetRepository @Inject constructor(
    private val koreaCoronaWidgetInformationDao: KoreaCoronaWidgetInformationDao,
    private val localCoronaWidgetInformationDao: LocalCoronaWidgetInformationDao
) {
    suspend fun selectByIdKoreaCoronaWidgetInformation(id: Int): KoreaCoronaWidgetInformation? {
        return koreaCoronaWidgetInformationDao.selectById(id)
    }

    suspend fun insert(information: KoreaCoronaWidgetInformation): Long {
        return koreaCoronaWidgetInformationDao.insert(information)
    }

    suspend fun deleteByIdKoreaCoronaWidgetInformation(ids: IntArray): Int {
        return koreaCoronaWidgetInformationDao.deleteById(ids)
    }

    suspend fun selectByIdLocalCoronaWidgetInformation(id: Int): LocalCoronaWidgetInformation? {
        return localCoronaWidgetInformationDao.selectById(id)
    }

    suspend fun insert(information: LocalCoronaWidgetInformation): Long {
        return localCoronaWidgetInformationDao.insert(information)
    }

    suspend fun deleteByIdLocalCoronaWidgetInformation(ids: IntArray): Int {
        return koreaCoronaWidgetInformationDao.deleteById(ids)
    }
}