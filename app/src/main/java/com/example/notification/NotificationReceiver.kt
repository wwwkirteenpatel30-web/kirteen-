package com.example.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Medication Reminder"
        val message = intent.getStringExtra("message") ?: "Time to take your medication."
        val isExpiry = intent.getBooleanExtra("isExpiry", false)

        NotificationHelper.triggerInstantNotification(context, title, message, isExpiry)
    }
}
