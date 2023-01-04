package com.example.noteapp.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.DialogEditNoteBinding
import com.example.noteapp.databinding.FragmentNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment: Fragment(R.layout.fragment_notes),NoteAdapter.OnItemClickListener {
    private lateinit var binding: FragmentNotesBinding
    private val viewModel: NoteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)
        val notesAdapter = NoteAdapter(this)

        binding.apply {
            rvNotes.adapter = notesAdapter
            rvNotes.setHasFixedSize(true)
        }

        binding.btAddNote.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToNewNoteFragment()
            findNavController().navigate(action)
        }

        viewModel.notes.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                binding.rvNotes.isVisible = true
                binding.tvNoNotes.isVisible = false
            }else{
                binding.rvNotes.isVisible = false
                binding.tvNoNotes.isVisible = true
            }
            notesAdapter.submitList(it)
        }
    }

    override fun onItemClick(note: Note) {
        val id = note.id
        val action = NotesFragmentDirections.actionNotesFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }
}