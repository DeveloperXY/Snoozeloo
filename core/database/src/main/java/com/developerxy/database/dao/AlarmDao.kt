package com.developerxy.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.developerxy.database.entity.AlarmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarms")
    fun getAll(): Flow<List<AlarmEntity>>

    @Insert
    suspend fun insertAll(vararg alarms: AlarmEntity)

    @Update
    suspend fun update(alarm: AlarmEntity): Int

    @Delete
    suspend fun delete(alarm: AlarmEntity)
}