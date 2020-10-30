package ru.cutepool.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.cutepool.todolist.data.local.dao.NoteDao
import ru.cutepool.todolist.data.local.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}
