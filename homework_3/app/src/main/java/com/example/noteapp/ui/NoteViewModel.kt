package com.example.noteapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.Note
import com.example.noteapp.data.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(private val dao: NoteDao) : ViewModel() {

    private val noteFlow = dao.getNotes()
    val notes = noteFlow.asLiveData()

    fun addNote(title: String, body: String) {
        viewModelScope.launch {
            dao.insert(Note(title = title, body = body))
        }
    }

    fun updateNote(note: Note, title: String, body: String) {
        viewModelScope.launch {
            dao.update(note.copy(title = title, body = body))
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            dao.delete(note)
        }
    }
}