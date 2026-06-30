package com.example.data.local

import androidx.room.*
import com.example.data.model.IntakeRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface IntakeDao {
    @Query("SELECT * FROM intake_records WHERE medicationId = :medicationId ORDER BY dateString DESC, timeString DESC")
    fun getRecordsForMedication(medicationId: Int): Flow<List<IntakeRecord>>

    @Query("SELECT * FROM intake_records WHERE medicationId IN (:medicationIds) ORDER BY dateString DESC, timeString DESC")
    fun getRecordsForMedications(medicationIds: List<Int>): Flow<List<IntakeRecord>>

    @Query("SELECT * FROM intake_records WHERE medicationId = :medicationId AND dateString = :dateString AND timeString = :timeString LIMIT 1")
    suspend fun getRecord(medicationId: Int, dateString: String, timeString: String): IntakeRecord?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: IntakeRecord)

    @Delete
    suspend fun deleteRecord(record: IntakeRecord)

    @Query("SELECT * FROM intake_records")
    fun getAllRecordsFlow(): Flow<List<IntakeRecord>>
}
