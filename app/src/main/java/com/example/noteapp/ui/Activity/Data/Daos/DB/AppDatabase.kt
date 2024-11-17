package com.example.noteapp.ui.Activity.Data.Daos.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.ui.Activity.Data.Daos.Models.NoteModels


@Database(entities = [NoteModels::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}