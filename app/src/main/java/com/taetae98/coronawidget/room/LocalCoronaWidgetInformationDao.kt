package com.taetae98.coronawidget.room

import androidx.room.Dao
import androidx.room.Query
import com.taetae98.coronawidget.base.BaseDao
import com.taetae98.coronawidget.dto.LocalCoronaWidgetInformation

@Dao
interface LocalCoronaWidgetInformationDao : BaseDao<LocalCoronaWidgetInformation> {
    @Query("SELECT * FROM LocalCoronaWidgetInformation WHERE id = :id")
    suspend fun selectById(id: Int): LocalCoronaWidgetInformation?

    @Query("DELETE FROM LocalCoronaWidgetInformation WHERE id IN (:ids)")
    suspend fun deleteById(ids: IntArray): Int
}