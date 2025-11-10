package com.example.memento.helpers

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import com.example.memento.R
import com.example.memento.entities.Task
import com.example.memento.entities.TaskCategory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewTaskDialogHelper(private val view: View) {
    private val selectedDateTime: Calendar = Calendar.getInstance()

    private val sdfDate = SimpleDateFormat("dd.MM.yyyy.", Locale.ENGLISH)
    private val sdfTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    private val spinner = view.findViewById<Spinner>(R.id.spn_new_task_dialog_category)
    private val dateSelection = view.findViewById<EditText>(R.id.et_new_task_dialog_date)
    private val timeSelection = view.findViewById<EditText>(R.id.et_new_task_dialog_time)

    fun populateSpinner(categories: List<TaskCategory>) {
        val spinnerAdapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_spinner_item,
            categories
        )
        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = spinnerAdapter
    }

    fun activateDateTimeListeners() {
        dateSelection.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    DatePickerDialog(
                        view.context,
                        object : DatePickerDialog.OnDateSetListener {
                            override fun onDateSet(
                                d: DatePicker?,
                                year: Int,
                                monthOfYear: Int,
                                dayOfMonth: Int
                            ) {
                                selectedDateTime.set(year, monthOfYear, dayOfMonth)
                                dateSelection.setText(sdfDate.format(selectedDateTime.time).toString())
                            }

                        },
                        selectedDateTime.get(Calendar.YEAR),
                        selectedDateTime.get(Calendar.MONTH),
                        selectedDateTime.get(Calendar.DAY_OF_MONTH)
                    ).show()
                    view.clearFocus()
                }
            }
        }

        timeSelection.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    TimePickerDialog(
                        view.context,
                        object : TimePickerDialog.OnTimeSetListener {
                            override fun onTimeSet(
                                t: TimePicker?,
                                hourOfDay: Int,
                                minute: Int
                            ) {
                                selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                selectedDateTime.set(Calendar.MINUTE, minute)
                                timeSelection.setText(sdfTime.format(selectedDateTime.time).toString())
                            }
                        },
                        selectedDateTime.get(Calendar.HOUR_OF_DAY),
                        selectedDateTime.get(Calendar.MINUTE),
                        true
                    ).show()
                    view.clearFocus()
                }
            }
        }
    }

    fun buildTask(): Task {
        val etName = view.findViewById<EditText>(R.id.et_new_task_dialog_name)
        val newTaskName = etName.text.toString()
        val spinnerCategory = view.findViewById<Spinner>(R.id.spn_new_task_dialog_category)
        val selectedCategory = spinnerCategory.selectedItem as TaskCategory

        return Task(
            0,
            newTaskName,
            selectedDateTime.time,
            selectedCategory.id,
            false
        )
    }
}
