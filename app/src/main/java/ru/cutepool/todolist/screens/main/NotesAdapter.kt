package ru.cutepool.todolist.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*
import ru.cutepool.todolist.R
import ru.cutepool.todolist.data.local.entity.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteHolder>() {

    private var notes: SortedList<Note> = SortedList(Note::class.java, object : SortedList.Callback<Note>() {

        override fun areItemsTheSame(item1: Note?, item2: Note?): Boolean {
            return if (item1 != null && item2 != null) {
                item1.id == item2.id
            } else {
                false
            }
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }

        override fun compare(o1: Note?, o2: Note?): Int {
            return  if (o1 != null && o2 != null) {
                (o2.timestamp - o1.timestamp).toInt()
            } else {
                0
            }
        }

        override fun areContentsTheSame(oldItem: Note?, newItem: Note?): Boolean {
            return if (oldItem != null && newItem != null) {
                oldItem == newItem
            } else {
                false
            }
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int = notes.size()


    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notes[position])
    }

    fun setItems(notes: List<Note>) {
        this.notes.replaceAll(notes)
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        fun bind(note: Note) {
            item_note__text.text = note.text
        }

        override val containerView: View?
            get() = itemView


    }

}