package ru.cutepool.todolist.screens.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.view.*
import ru.cutepool.todolist.R
import ru.cutepool.todolist.activities.MainViewModel
import ru.cutepool.todolist.activities.MainViewModelFactory
import ru.cutepool.todolist.data.local.DatabaseBuilder
import ru.cutepool.todolist.data.local.DatabaseHelperImpl
import ru.cutepool.todolist.data.local.entity.Note
import ru.cutepool.todolist.utils.CiceroneHelper

class MainFragment : Fragment() {

    private val notesAdapter: NotesAdapter = NotesAdapter()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        with(view) {
            frg_main__list_notes.layoutManager =
                LinearLayoutManager(container?.context, RecyclerView.VERTICAL, false)
            frg_main__list_notes.adapter = notesAdapter

            frg_main__fab_add.setOnClickListener {
                viewModel.insert(Note())
            }

            frg_main__toolbar.setOnClickListener {
                viewModel.toProfileScreen()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getNotes().observe(viewLifecycleOwner, Observer {
            Log.d("DEBUG", "onCreateView: setItems()")
            notesAdapter.setItems(it)
        })
    }

    private fun setupViewModel() {
        val viewModelFactory = MainViewModelFactory(
            CiceroneHelper.getRouter(),
            DatabaseHelperImpl(DatabaseBuilder.getInstance(requireActivity().applicationContext))
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    companion object {
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }

}
