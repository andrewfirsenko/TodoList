package ru.cutepool.todolist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import ru.cutepool.todolist.R
import ru.cutepool.todolist.data.local.DatabaseBuilder
import ru.cutepool.todolist.data.local.DatabaseHelperImpl
import ru.cutepool.todolist.screens.main.MainFragment
import ru.cutepool.todolist.screens.profile.ProfileFragment
import ru.cutepool.todolist.utils.AnimateSupportAppNavigator
import ru.cutepool.todolist.utils.CiceroneHelper
import ru.terrakok.cicerone.android.pure.AppNavigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class MainActivity : AppCompatActivity() {

    private val navigatorHolder = CiceroneHelper.getNavHolder()
    private val navigator = object : SupportAppNavigator(this, supportFragmentManager, CONTAINER) {
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

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = MainViewModelFactory(CiceroneHelper.getRouter(), DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.createMainScreen()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    companion object {
        const val CONTAINER = R.id.act_main__container
    }

}
