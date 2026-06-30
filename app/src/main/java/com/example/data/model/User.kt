package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val passwordHash: String,
    val displayName: String,
    val avatarUrl: String = "",
    val isLoggedIn: Boolean = false,
    val enableReminders: Boolean = true,
    val dailyReminderTime: String = "08:00"
)
