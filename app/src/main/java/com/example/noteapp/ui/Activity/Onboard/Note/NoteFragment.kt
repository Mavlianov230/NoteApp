package com.example.noteapp.ui.Activity.Onboard.Note

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.ui.Activity.App
import com.example.noteapp.ui.Activity.Data.Daos.Models.NoteModels
import com.example.noteapp.ui.Activity.Interface.OnClickItem
import com.example.noteapp.ui.Activity.Onboard.Adapter.NoteAdapter
import com.example.noteapp.ui.Activity.MainActivity
import androidx.core.view.GravityCompat

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)
    private var isListLayout = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        getData()
    }

    private fun initialize() {
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNote.adapter = noteAdapter
    }

    private fun setupListener() = with(binding) {
        btnChanger.setOnClickListener {
            toggleLayout()
        }
        btnMenu.setOnClickListener {
            val activity = requireActivity() as MainActivity
            activity.drawerLayout.openDrawer(GravityCompat.START)
        }

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun toggleLayout() {

        if (isListLayout) {
            binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.btnChanger.setImageResource(R.drawable.baseline_view_headline_24)
        } else {
            binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
            binding.btnChanger.setImageResource(R.drawable.baseline_view_quilt_24)
        }
        isListLayout = !isListLayout
    }

    private fun getData() {

        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { item ->
            noteAdapter.submitList(item)
        }
    }

    override fun onLongClick(noteModels: NoteModels) {

        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { dialog, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModels)
                Toast.makeText(requireContext(), "Заметка удалена", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }

    override fun onClick(noteModels: NoteModels) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModels.id)
        findNavController().navigate(action)
    }
}
