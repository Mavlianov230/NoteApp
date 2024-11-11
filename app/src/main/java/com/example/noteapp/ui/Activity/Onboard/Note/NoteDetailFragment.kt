package com.example.noteapp.ui.Activity.Onboard.Note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.ui.Activity.App
import com.example.noteapp.ui.Activity.Data.Daos.Models.NoteModels
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() = with(binding) {

        returnBtn.setOnClickListener {
            findNavController().navigateUp()
        }


        btnAddText.setOnClickListener {
            val etTitle = etTitle.text.toString()
            val etDescriptor = etDescription.text.toString()
            val currentDate = getCurrentDate()
            val currentTime = getCurrentTime()


            App.appDatabase?.noteDao()?.insertNote(NoteModels(
                etTitle, etDescriptor, currentDate, currentTime
            ))


            time.text = currentTime
            date.text = currentDate


            findNavController().navigateUp()
        }
    }


    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun getCurrentTime(): String {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.format(Date())
    }
}

