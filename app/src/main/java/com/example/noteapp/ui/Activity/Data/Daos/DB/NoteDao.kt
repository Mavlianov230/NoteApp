package com.example.noteapp.ui.Activity.Data.Daos.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp.ui.Activity.Data.Daos.Models.NoteModels


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModels: NoteModels)

    @Query("SELECT * FROM noteModels")
    fun getAll():LiveData<List<NoteModels>>
}
