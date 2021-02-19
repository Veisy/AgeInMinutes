package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDatePicker()
    }


    //Date Picker initializer
    private fun initDatePicker() {
        binding.buttonSelectDate.setOnClickListener{

            val calendar : Calendar = Calendar.getInstance()

            DatePickerDialog(this,this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    //When date selected
    override fun onDateSet(view: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int) {
        val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
        val textBirthDate = "Birth date is $selectedDate"
        binding.textViewSelectedDate.text = textBirthDate

        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
        val birthDate: Date? = sdf.parse(selectedDate)

        val differenceInMilliseconds = Calendar.getInstance().timeInMillis - (birthDate?.time ?: 0)
        val millisInDay = 86_400_000
        val ageInDays = (differenceInMilliseconds / millisInDay).toInt()

        val textAgeInDays = "Age in Days is $ageInDays"
        binding.textViewAgeInDays.text = textAgeInDays
    }

}