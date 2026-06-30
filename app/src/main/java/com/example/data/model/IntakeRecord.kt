package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intake_records")
data class IntakeRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val medicationId: Int,
    val dateString: String, // format "yyyy-MM-dd"
    val timeString: String, // format "HH:mm"
    val status: String // "TAKEN", "MISSED"
)
