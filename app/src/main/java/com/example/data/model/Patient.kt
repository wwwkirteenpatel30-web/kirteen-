package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int, // The doctor/provider tracking this patient
    val name: String, // Patient Name (કોણ)
    val age: Int = 42,
    val gender: String = "Male",
    val doctorName: String, // Treating Doctor Name (ડોક્ટર)
    val startDate: Long, // Treatment Start Date (ક્યારે)
    val medicalHistory: String, // Medical History (રોગની વિગતો)
    val diagnosisDetails: String, // Diagnosis Details (રોગની સ્થિતિ)
    val hospitalName: String = "KEM Hospital, Mumbai", // Indian Local Hospital
    val opdFormat: String = "GOVT-OPD/2026-X91", // Government hospital OPD format support
    val primaryManufacturer: String = "Cipla", // Indian drug company recognition (કંપનીની ઓળખ)
    val doctorNotes: String = "" // Doctor's Notes/Recommendations
) {
    fun getFormattedDate(): String {
        val sdf = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(startDate))
    }
}
