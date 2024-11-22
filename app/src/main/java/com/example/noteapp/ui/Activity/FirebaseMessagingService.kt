package com.example.noteapp.ui.Activity

import android.content.ContentValues.TAG
import com.google.firebase.messaging.FirebaseMessagingService
import android.util.Log

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {

    }
}
