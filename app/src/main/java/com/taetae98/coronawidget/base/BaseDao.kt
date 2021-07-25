package com.taetae98.coronawidget.base

import androidx.room.*

@Dao
interface BaseDao<E: Any> {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(elements: E): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(elements: Array<E>): LongArray

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(elements: List<E>): LongArray

    @Transaction
    @Delete
    suspend fun delete(elements: E): Int

    @Transaction
    @Delete
    suspend fun delete(elements: Array<E>): Int

    @Transaction
    @Delete
    suspend fun delete(elements: List<E>): Int

    @Transaction
    @Update
    suspend fun update(elements: E): Int

    @Transaction
    @Update
    suspend fun update(elements: Array<E>): Int

    @Transaction
    @Update
    suspend fun update(elements: List<E>): Int
}