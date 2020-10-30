package ru.cutepool.todolist.screens

import androidx.fragment.app.Fragment
import ru.cutepool.todolist.screens.main.MainFragment
import ru.cutepool.todolist.screens.profile.ProfileFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class MainScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return MainFragment.newInstance()
        }
    }

    class ProfileScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return ProfileFragment.newInstance()
        }
    }

}
