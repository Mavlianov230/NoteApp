package com.example.noteapp.ui.Activity

import android.app.Application
import com.example.noteapp.ui.Activity.utils.PreferenceHelper

class App : Application() {
    companion object {
        lateinit var preferences: PreferenceHelper
    }

    override fun onCreate() {
        super.onCreate()
        preferences = PreferenceHelper()
        preferences.unit(this)
    }
}
