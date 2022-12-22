package com.example.noteapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.NoteItemLayoutBinding

class NoteAdapter(private val listener: OnItemClickListener):ListAdapter<Note, NoteAdapter.NoteViewModel>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewModel {
        val binding = NoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewModel(binding)
    }

    override fun onBindViewHolder(holder: NoteViewModel, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class NoteViewModel(private val binding: NoteItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val note = getItem(position)
                        listener.onItemClick(note)
                    }
                }
            }
        }

        fun bind(note: Note) {
            binding.apply {
                tvTitle.text = note.title
                tvBody.text = note.body
            }

        }
    }
    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }

    class DiffCallback :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}