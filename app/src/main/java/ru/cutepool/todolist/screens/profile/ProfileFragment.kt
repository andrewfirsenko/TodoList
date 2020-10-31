package ru.cutepool.todolist.screens.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_profile.view.*
import ru.cutepool.todolist.R
import ru.cutepool.todolist.activities.MainViewModel
import ru.cutepool.todolist.activities.MainViewModelFactory
import ru.cutepool.todolist.data.local.DatabaseBuilder
import ru.cutepool.todolist.data.local.DatabaseHelperImpl
import ru.cutepool.todolist.utils.CiceroneHelper

class ProfileFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        container?.clipToPadding = false
        container?.clipChildren = false

        with(view) {
            frg_profile__toolbar.setOnClickListener {
                frg_profile__part_fragment.visibility = View.INVISIBLE
                viewModel.toMainScreen()
            }
        }

        return view
    }

    private fun setupViewModel() {
        val viewModelFactory = MainViewModelFactory(
                CiceroneHelper.getRouter(),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(requireActivity().applicationContext))
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    companion object {
        fun newInstance(): Fragment = ProfileFragment()
    }

}
