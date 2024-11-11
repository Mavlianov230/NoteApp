package com.example.noteapp.ui.Activity.Onboard.Note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.Activity.App
import com.example.noteapp.ui.Activity.Onboard.Adapter.NoteAdapter
import com.example.noteapp.ui.Activity.utils.PreferenceHelper


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

      binding.rvNote.apply {
          layoutManager = LinearLayoutManager(requireContext())
          adapter = noteAdapter
      }
    }

    private fun setupListener() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)


        }
    }

    private fun getData() {
       App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){item ->
           noteAdapter.submitList(item)
       }
    }


}