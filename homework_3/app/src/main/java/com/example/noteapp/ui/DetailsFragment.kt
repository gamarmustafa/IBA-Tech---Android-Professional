package com.example.noteapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentDetailsBinding

class DetailsFragment:Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: NoteViewModel by activityViewModels()
    private val args : DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        val id = args.id

        viewModel.notes.observe(viewLifecycleOwner){
            for(note in it){
                if(id == note.id){
                    binding.apply {
                        tvTitle.text = note.title
                        tvBody.text = note.body
                    }
                }
            }
        }
    }
}