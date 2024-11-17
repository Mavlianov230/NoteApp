package com.example.noteapp.ui.Activity.Data.Daos.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.R

@Entity(tableName = "noteModels")
data class NoteModels(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val selectedColor: Int = R.color.gray
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}