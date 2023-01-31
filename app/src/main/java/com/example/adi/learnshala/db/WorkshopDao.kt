package com.example.adi.learnshala.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkshopDao {

    @Insert
    suspend fun insertWorkshopList(workshops: List<WorkshopEntity>)

    @Update
    suspend fun update(workshopEntity: WorkshopEntity)

    @Query("select * from `workshopTable`")
    fun fetchAllPlaces(): Flow<List<WorkshopEntity>>
}