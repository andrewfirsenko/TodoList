package ru.cutepool.todolist.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.cutepool.todolist.data.local.entity.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

//    @Query("SELECT * FROM notes WHERE id = :id")
//    suspend fun findById(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

}
