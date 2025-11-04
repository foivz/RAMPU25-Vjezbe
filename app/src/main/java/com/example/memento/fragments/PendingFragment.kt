package com.example.memento.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memento.MockDataLoader
import com.example.memento.R
import com.example.memento.adapters.TasksAdapter
import com.example.memento.helpers.NewTaskDialogHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PendingFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab = view.findViewById(R.id.fab_pending_fragment_create_task)
        recyclerView = view.findViewById(R.id.rv_pending_tasks)
        recyclerView.adapter = TasksAdapter(MockDataLoader.getDemoData())
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        fab.setOnClickListener {
            showDialog()
        }




    }

    private fun showDialog() {
        val newTaskDialogView = LayoutInflater
            .from(context)
            .inflate(R.layout.new_task_dialog, null)

        val dialogHelper = NewTaskDialogHelper(newTaskDialogView)
        dialogHelper.populateSpinner(
            MockDataLoader.getDemoCategories()
        )
        dialogHelper.activateDateTimeListeners()

        AlertDialog.Builder(context)
            .setView(newTaskDialogView)
            .setTitle(getString(R.string.create_a_new_task))
            .setPositiveButton(getString(R.string.create_a_new_task)) { _, _ ->
                val newTask = dialogHelper.buildTask()
                val tasksAdapter = (recyclerView.adapter as TasksAdapter)
                tasksAdapter.addTask(newTask)
            }
            .show()
    }
}










