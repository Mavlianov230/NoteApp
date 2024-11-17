package com.example.noteapp.ui.Activity.Data.Daos.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.ui.Activity.Data.Daos.Models.NoteModels


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteModels)

    @Query("SELECT * FROM noteModels")
    fun getAll():LiveData<List<NoteModels>>

    @Delete
    fun deleteNote(noteModels: NoteModels)


    @Update
    fun updateNote(note: NoteModels)

    @Query("SELECT * FROM noteModels WHERE id = :id")
    fun getById(id: Int): NoteModels?
}

