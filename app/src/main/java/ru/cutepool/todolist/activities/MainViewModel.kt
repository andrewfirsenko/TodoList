package ru.cutepool.todolist.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.cutepool.todolist.data.local.DatabaseHelper
import ru.cutepool.todolist.data.local.entity.Note
import ru.cutepool.todolist.screens.Screens
import ru.terrakok.cicerone.Router

class MainViewModel(private val router: Router, private val dbHelper: DatabaseHelper) : ViewModel() {

    fun getNotes(): LiveData<List<Note>> {
        return dbHelper.getNotes()
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            dbHelper.insert(note)
        }
    }

    fun createMainScreen() {
        router.newRootScreen(Screens.MainScreen())
    }

    fun toProfileScreen() {
        router.navigateTo(Screens.ProfileScreen())
    }

    fun toMainScreen() {
        router.navigateTo(Screens.MainScreen())
    }

}
