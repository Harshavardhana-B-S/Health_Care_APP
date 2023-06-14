package com.example.healthcare

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_book_appointment.*
import java.util.Calendar

class BookAppointmentActivity : AppCompatActivity() {

    //create variables/instance
    private lateinit var title: TextView
    private lateinit var name: EditText
    private lateinit var address: EditText
    private lateinit var contactNum: EditText
    private lateinit var fees: EditText

    // Create Date and time picker variables/instance
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        //initialize variables with id
        title = findViewById(R.id.textViewAppTitle)
        name = findViewById(R.id.editTextAppName)
        address = findViewById(R.id.editTextAPPAddress)
        contactNum = findViewById(R.id.editTextAPPContactNum)
        fees = findViewById(R.id.editTextAPPFees)

        //EditText variables cannot change values
        name.keyListener = null
        address.keyListener = null
        contactNum.keyListener = null
        fees.keyListener = null

        //Retrieve Data from  intent
        val it = intent
        val titleInit = it.getStringExtra("text1")
        val nameInit = it.getStringExtra("text2")
        val addressInit = it.getStringExtra("text3")
        val contactInit = it.getStringExtra("text4")
        val feesInit = it.getStringExtra("text5")

        //assign intent data
        // Editable obj created from String, since string cannot be assigned to Editable variables
        title.text = titleInit
        name.text = Editable.Factory.getInstance().newEditable(nameInit)
        address.text = Editable.Factory.getInstance().newEditable(addressInit)
        contactNum.text = Editable.Factory.getInstance().newEditable(contactInit)
        fees.text = Editable.Factory.getInstance().newEditable("Fess:$feesInit/-")


        //in .xml file we have choosen Date id
        buttonAppDate.setOnClickListener {
            showDatePickerDialog()
        }

        buttonAppTime.setOnClickListener {
            showTimePickerDialog()
        }

        buttonBack.setOnClickListener{
            val it = Intent(this,FindDoctorActivity::class.java)
            startActivity(it)
        }
    }
    private fun showDatePickerDialog() {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog and set the initial date to the current date
        datePickerDialog = DatePickerDialog(this,
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, monthOfYear)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Compare the selected date with the current date
                if (selectedDate.after(currentDate)) {
                    // Update the selectedDate TextView with the selected date
                    val selectedDateText = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year)
                    this. buttonAppDate.text = selectedDateText
                } else {
                    Toast.makeText(this, "Please select a future date", Toast.LENGTH_SHORT).show()
                }
            }, year, month, day)

        // Set the minimum date to the current date
        datePickerDialog.datePicker.minDate = currentDate.timeInMillis

        // Show the DatePickerDialog
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {

        var selectedTime: String = ""

        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        // Create a TimePickerDialog and set the initial time to the current time
        timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                // Update the selectedTime variable with the selected time
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)

                // Update the buttonAppTime text with the selected time
                buttonAppTime.text = selectedTime
            },
            hour,
            minute,
            true // Set the 24-hour format to true
        )

        // Show the TimePickerDialog
        timePickerDialog.show()
    }




}