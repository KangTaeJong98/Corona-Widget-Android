package com.taetae98.coronawidget.room

import androidx.room.Dao
import androidx.room.Query
import com.taetae98.coronawidget.base.BaseDao
import com.taetae98.coronawidget.dto.KoreaCoronaWidgetInformation

@Dao
interface KoreaCoronaWidgetInformationDao : BaseDao<KoreaCoronaWidgetInformation> {
    @Query("SELECT * FROM KoreaCoronaWidgetInformation WHERE id = :id")
    suspend fun selectById(id: Int): KoreaCoronaWidgetInformation?

    @Query("DELETE FROM KoreaCoronaWidgetInformation WHERE id IN (:ids)")
    suspend fun deleteById(ids: IntArray): Int
}