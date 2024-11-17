package com.example.noteapp.ui.Activity

import android.app.Application
import androidx.room.Room
import com.example.noteapp.ui.Activity.Data.Daos.DB.AppDatabase
import com.example.noteapp.ui.Activity.utils.PreferenceHelper



class App : Application() {
    companion object {
        lateinit var preferences: PreferenceHelper
        var appDatabase: AppDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()
        preferences = PreferenceHelper()
        preferences.unit(this)
       val  sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        getInstance()
    }

    private fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context ,
                    AppDatabase::class.java,
                    "note.database"

                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }


}
