package ru.cutepool.todolist.data.local

import androidx.lifecycle.LiveData
import ru.cutepool.todolist.data.local.entity.Note

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override fun getNotes(): LiveData<List<Note>> = appDatabase.noteDao().getAll()

    override suspend fun insert(note: Note) = appDatabase.noteDao().insert(note)

}
