package com.example.noteapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddNewNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewNoteFragment:Fragment(R.layout.fragment_add_new_note) {
    private lateinit var binding: FragmentAddNewNoteBinding
    private val viewModel: NoteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNewNoteBinding.bind(view)

        binding.btSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val body = binding.etBody.text.toString()
            viewModel.addNote(title,body)
            val action = NewNoteFragmentDirections.actionNewNoteFragmentToNotesFragment()
            findNavController().navigate(action)
        }
    }
}