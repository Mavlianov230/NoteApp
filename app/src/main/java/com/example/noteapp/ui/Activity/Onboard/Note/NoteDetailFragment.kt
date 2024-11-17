package com.example.noteapp.ui.Activity.Onboard.Note

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
    private var noteId: Int = -1
    private var selectedColor: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateNote()
        setupListeners()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }

        if (noteId != -1) {
            val note = App.appDatabase?.noteDao()?.getById(noteId)
            note?.let { model ->
                binding.etTitle.setText(model.title)
                binding.etDescription.setText(model.description)
                binding.date.text = model.date
                binding.time.text = model.time
                selectedColor = model.selectedColor
                updateNoteColor(selectedColor)

            }
        }
    }



    private fun setupListeners() = with(binding) {


        etTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    showBtnAddText()
                } else {
                    hideBtnAddText()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        menuBtn.setOnClickListener {
            toggleColorsVisibility(true)
        }
        color1.setOnClickListener { selectColor(R.color.color_1) }
        color2.setOnClickListener { selectColor(R.color.color_2) }
        color3.setOnClickListener { selectColor(R.color.color_3) }
        color4.setOnClickListener { selectColor(R.color.color_4) }
        color5.setOnClickListener { selectColor(R.color.color_5) }
        color6.setOnClickListener { selectColor(R.color.color_6) }


        etDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    showBtnAddText()
                } else {
                    hideBtnAddText()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        returnBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        btnAddText.setOnClickListener {
            val etTitle = etTitle.text.toString()
            val etDescription = etDescription.text.toString()
            val currentDate = getCurrentDate()
            val currentTime = getCurrentTime()

            if (noteId != -1) {
                val updatedNote = NoteModels(
                    etTitle, etDescription,
                    date = currentDate,
                    time = currentTime,
                    selectedColor = selectedColor
                )
                updatedNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updatedNote)
            } else {
                App.appDatabase?.noteDao()?.insertNote(
                    NoteModels(etTitle, etDescription, currentDate, currentTime,selectedColor)
                )
            }


            binding.time.text = currentTime
            binding.date.text = currentDate
            findNavController().navigateUp()

        }
    }

    private fun toggleColorsVisibility(visible: Boolean) {
        if (visible) {
            binding.colorsContainer.visibility = View.VISIBLE
        } else {
            binding.colorsContainer.visibility = View.GONE
        }
    }

    private fun selectColor(colorResId: Int) {
        selectedColor = colorResId

        toggleColorsVisibility(false)
        updateNoteColor(colorResId)
    }

    private fun updateNoteColor(colorResId: Int) {

        binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), colorResId))
    }


    private fun showBtnAddText() {
        binding.btnAddText.visibility = View.VISIBLE
        binding.btnAddText.animate().translationX(0f).setDuration(300).start()
    }

    private fun hideBtnAddText() {
        binding.btnAddText.visibility = View.GONE
        binding.btnAddText.animate().translationX(200f).setDuration(300).start()
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
