package com.example.noteapp.ui.Activity.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var shaerdPreferences: SharedPreferences

    fun unit(context: Context) {
        shaerdPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    }

    var text: String?
        get() = shaerdPreferences.getString("text", "")
        set(value) = shaerdPreferences.edit().putString("text", value).apply()

    var isOnBoardShown: Boolean
        get() = shaerdPreferences.getBoolean("board", false)
    set(value) = shaerdPreferences.edit().putBoolean("board", value).apply()

}