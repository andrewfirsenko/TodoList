package ru.cutepool.todolist.data.local

import androidx.lifecycle.LiveData
import ru.cutepool.todolist.data.local.entity.Note

interface DatabaseHelper {

    fun getNotes(): LiveData<List<Note>>

    suspend fun insert(note: Note)

}
