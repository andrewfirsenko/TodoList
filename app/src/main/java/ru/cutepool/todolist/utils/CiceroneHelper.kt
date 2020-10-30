package ru.cutepool.todolist.utils

import ru.cutepool.todolist.App
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

object CiceroneHelper {

    fun getRouter(): Router {
        return App.INSTANCE.router
    }

    fun getNavHolder(): NavigatorHolder {
        return App.INSTANCE.navigatorHolder
    }
}
