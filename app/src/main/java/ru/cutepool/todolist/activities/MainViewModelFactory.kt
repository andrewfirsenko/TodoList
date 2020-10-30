package ru.cutepool.todolist.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.cutepool.todolist.data.local.DatabaseHelper
import ru.terrakok.cicerone.Router

class MainViewModelFactory(private val router: Router, private val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Router::class.java, DatabaseHelper::class.java)
            .newInstance(router, dbHelper)
    }

}
