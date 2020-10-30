package ru.cutepool.todolist.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.cutepool.todolist.R
import ru.cutepool.todolist.screens.main.MainFragment
import ru.cutepool.todolist.screens.profile.ProfileFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class AnimateSupportAppNavigator(
        activity: FragmentActivity,
        fragmentManager: FragmentManager,
        containerId: Int)
    : SupportAppNavigator(activity,  fragmentManager, containerId) {

    override fun setupFragmentTransaction(
            command: Command,
            currentFragment: Fragment?,
            nextFragment: Fragment?,
            fragmentTransaction: FragmentTransaction) {

        when (currentFragment) {
            is MainFragment -> {
                fragmentTransaction.setCustomAnimations(R.anim.main_fragment_enter, R.anim.main_fragment_exit)
            }
            is ProfileFragment -> {
                fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit)
            }
        }

        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
    }
}
