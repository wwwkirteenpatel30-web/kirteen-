package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val patientId: Int = 0, // Linked patient ID reference
    val name: String, // Indian medicine name (e.g. Crocin, Pantocid)
    val regionalName: String = "", // Indian regional name (e.g. ક્રોસિન, પેન્ટોસિડ)
    val dosage: String,
    val companyName: String = "Cipla", // Indian pharmaceutical manufacturer
    val startDate: Long, // timestamp in ms
    val frequency: String, // "Daily", "Weekly", "As Needed"
    val reminderTime: String, // Comma-separated times, e.g. "08:00, 20:00"
    val durationDays: Int = 35, // default 35 days
    val pillsRemaining: Int = 30,
    val lowStockThreshold: Int = 5,
    val refillsLeft: Int = 3,
    val familyMember: String = "Self", // "Self", "Sarah (Daughter)", etc.
    val reminderSound: String = "Zen Bell" // "Zen Bell", "Chime", "Alert Pulse", "Forest Birds"
) {
    fun getExpiryDate(): Long {
        val msPerDay = 24 * 60 * 60 * 1000L
        return startDate + (durationDays * msPerDay)
    }

    fun getDaysRemaining(currentTimeMillis: Long): Int {
        val msPerDay = 24 * 60 * 60 * 1000L
        val expiryTime = getExpiryDate()
        val remainingMs = expiryTime - currentTimeMillis
        return (remainingMs / msPerDay).toInt().coerceAtLeast(0)
    }

    fun isNearingExpiry(currentTimeMillis: Long): Boolean {
        val days = getDaysRemaining(currentTimeMillis)
        return days in 1..3
    }

    fun isExpired(currentTimeMillis: Long): Boolean {
        return getDaysRemaining(currentTimeMillis) == 0
    }
}
