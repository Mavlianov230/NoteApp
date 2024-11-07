package com.example.noteapp.ui.Activity

import android.app.Application
import com.example.noteapp.ui.Activity.utils.PreferenceHelper

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
    }

}