package com.example.noteapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes_table")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title:String,
    val body:String
):Parcelable
