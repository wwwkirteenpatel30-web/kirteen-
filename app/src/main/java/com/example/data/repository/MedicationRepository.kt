package com.example.data.repository

import com.example.data.local.MedicationDao
import com.example.data.local.IntakeDao
import com.example.data.local.PatientDao
import com.example.data.model.Medication
import com.example.data.model.IntakeRecord
import com.example.data.model.Patient
import kotlinx.coroutines.flow.Flow

class MedicationRepository(
    private val medicationDao: MedicationDao,
    private val intakeDao: IntakeDao,
    private val patientDao: PatientDao
) {
    // Patients Management
    fun getPatients(userId: Int): Flow<List<Patient>> =
        patientDao.getPatientsForUser(userId)

    suspend fun getPatientById(id: Int): Patient? =
        patientDao.getPatientById(id)

    suspend fun insertPatient(patient: Patient): Long =
        patientDao.insertPatient(patient)

    suspend fun updatePatient(patient: Patient) =
        patientDao.updatePatient(patient)

    suspend fun deletePatient(patient: Patient) =
        patientDao.deletePatient(patient)

    fun getMedications(userId: Int): Flow<List<Medication>> =
        medicationDao.getMedicationsForUser(userId)

    suspend fun getMedicationById(id: Int): Medication? =
        medicationDao.getMedicationById(id)

    suspend fun insertMedication(medication: Medication): Long =
        medicationDao.insertMedication(medication)

    suspend fun updateMedication(medication: Medication) =
        medicationDao.updateMedication(medication)

    suspend fun deleteMedication(medication: Medication) =
        medicationDao.deleteMedication(medication)

    // Intake Records
    fun getIntakeRecords(medicationId: Int): Flow<List<IntakeRecord>> =
        intakeDao.getRecordsForMedication(medicationId)

    fun getIntakeRecordsForMultiple(medicationIds: List<Int>): Flow<List<IntakeRecord>> =
        intakeDao.getRecordsForMedications(medicationIds)

    val allIntakeRecords: Flow<List<IntakeRecord>> = intakeDao.getAllRecordsFlow()

    suspend fun recordIntake(medicationId: Int, dateString: String, timeString: String, status: String) {
        val record = IntakeRecord(
            medicationId = medicationId,
            dateString = dateString,
            timeString = timeString,
            status = status
        )
        intakeDao.insertRecord(record)
    }

    suspend fun deleteIntakeRecord(medicationId: Int, dateString: String, timeString: String) {
        val existing = intakeDao.getRecord(medicationId, dateString, timeString)
        if (existing != null) {
            intakeDao.deleteRecord(existing)
        }
    }
}
