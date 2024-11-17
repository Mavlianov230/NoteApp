package com.example.noteapp.ui.Activity.Interface

import com.example.noteapp.ui.Activity.Data.Daos.Models.NoteModels

interface OnClickItem {
    fun onLongClick(noteModels: NoteModels)
    fun onClick(noteModels: NoteModels)
}
