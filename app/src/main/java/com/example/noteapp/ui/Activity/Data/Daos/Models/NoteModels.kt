package com.example.noteapp.ui.Activity.Data.Daos.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModels")
data class NoteModels(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
